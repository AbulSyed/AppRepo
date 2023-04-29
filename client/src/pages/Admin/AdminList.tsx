import { Avatar, List } from 'antd';
import { format, parseISO } from 'date-fns';
import {
  ExclamationOutlined,
  CheckOutlined,
} from '@ant-design/icons';
import { updateFeedbackResolvedStatus } from '../../store/feedback/feedbackSlice';
import { useAppDispatch } from '../../store/hooks';

interface AdminListProps {
  data: {
    id: number;
    area: "ISSUE" | "SUGGESTION" | "OTHER";
    comments: any;
    dateTime: string;
    message: string;
    author: string;
    authorImg: string;
    resolved: boolean;
  }[]
}

const AdminList: React.FC<AdminListProps> = ({ data }) => {
  const dispatch = useAppDispatch();
  console.log(data);

  const handleClick = (area: string, id: number) => {
    if (area === "ISSUE" || area === "SUGGESTION" || area === "OTHER") {
      dispatch(updateFeedbackResolvedStatus({ area, id }));
    }
  }

  return (
    <List
      itemLayout="horizontal"
      dataSource={data}
      renderItem={(item, index) => (
        <List.Item
            actions={item.resolved ? [<CheckOutlined />] : [<ExclamationOutlined />]}
            style={{background: item.resolved ?  '#66cc00' : '#ff6666', margin: '10px', borderRadius: '5px'}}
          >
          <List.Item.Meta
            avatar={<Avatar src={item.authorImg} />}
            title={item.author}
            description={item.message}
          />
          <div>
            <div>{format(parseISO(item.dateTime), 'dd/MM/yyyy')}</div>
            {
              !item.resolved ? <button onClick={() => handleClick(item.area, item.id)}>Mark complete</button>
              : <button onClick={() => handleClick(item.area, item.id)}>Re open</button>
            }
          </div>
        </List.Item>
      )}
    />
  )
};

export default AdminList;
import { Avatar, List } from 'antd';
import { format, parseISO } from 'date-fns';
import {
  ExclamationOutlined,
  CheckOutlined,
} from '@ant-design/icons';

interface AdminListProps {
  data: {
    id: number;
    area: string;
    comments: any;
    dateTime: string;
    message: string;
    author: string;
    authorImg: string;
    resolved: boolean;
  }[]
}

const AdminList: React.FC<AdminListProps> = ({ data }) => {
  return (
    <List
      itemLayout="horizontal"
      dataSource={data}
      renderItem={(item, index) => (
        <List.Item
            actions={item.resolved ? [<CheckOutlined />] : [<ExclamationOutlined />]}
          >
          <List.Item.Meta
            avatar={<Avatar src={item.authorImg} />}
            title={item.author}
            description={item.message}
          />
          <div>
            <div>{format(parseISO(item.dateTime), 'dd/MM/yyyy')}</div>
            {
              !item.resolved && <button>Mark complete</button>
            }
          </div>
        </List.Item>
      )}
    />
  )
};

export default AdminList;
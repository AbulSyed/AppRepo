import { useState } from 'react';
import { Avatar, List, Button, Modal, Form, Input } from 'antd';
import { format, parseISO } from 'date-fns';
import {
  ExclamationOutlined,
  CheckOutlined,
} from '@ant-design/icons';
import { updateFeedbackResolvedStatus, updateFeedbackResolvedStatusApiRequest } from '../../store/feedback/feedbackSlice';
import { useAppDispatch } from '../../store/hooks';
import Comment from '../../components/Comment';

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

  const handleClick = (area: string, id: number, resolved: boolean) => {
    if (area === "ISSUE" || area === "SUGGESTION" || area === "OTHER") {
      dispatch(updateFeedbackResolvedStatus({ area, id }));
      const updateResolvedData = {id, resolved};
      dispatch(updateFeedbackResolvedStatusApiRequest(updateResolvedData));
    }
  }

  return (
    <List
      itemLayout="horizontal"
      dataSource={data}
      renderItem={(item, index) => (
        <>
          <List.Item
              actions={item.resolved ? [<CheckOutlined />, <FormButton btnMessage="Add comment" />] : [<ExclamationOutlined />, <button>Add comment</button>]}
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
                !item.resolved ? <button onClick={() => handleClick(item.area, item.id, item.resolved)}>Mark complete</button>
                : <button onClick={() => handleClick(item.area, item.id, item.resolved)}>Re open</button>
              }
            </div>
          </List.Item>
          {
            item.comments.length > 0 && <h4>Comments</h4>
          }
          {
            item.comments.length > 0 && item.comments.map(comment => (
              <Comment key={comment.id} data={comment} />
            ))
          }
        </>
      )}
    />
  )
};

export default AdminList;

interface FormButtomProps {
  btnMessage: string;
}

// antd async close modal
const FormButton: React.FC<FormButtomProps> = ({ btnMessage }) => {
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [modalText, setModalText] = useState('Content of the modal');
  const [comment, setComment] = useState('');

  const showModal = () => {
    setOpen(true);
  };

  const handleOk = () => {
    setConfirmLoading(true);
    console.log(comment);
    setTimeout(() => {
      setOpen(false);
      setComment('');
      setConfirmLoading(false);
    }, 1000);
  };

  const handleCancel = () => {
    console.log('Clicked cancel button');
    setOpen(false);
  };

  return (
    <>
      <Button type="primary" onClick={showModal}>
        {btnMessage}
      </Button>
      <Modal
        open={open}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
      >
        <CustomForm comment={comment} setComment={setComment} />
      </Modal>
    </>
  )
}

type LayoutType = Parameters<typeof Form>[0]['layout'];

const CustomForm: React.FC = ({ comment, setComment }) => {
  const [form] = Form.useForm();
  const [formLayout, setFormLayout] = useState<LayoutType>('horizontal');

  const onFormLayoutChange = ({ layout }: { layout: LayoutType }) => {
    setFormLayout(layout);
  };

  const formItemLayout =
    formLayout === 'horizontal' ? { labelCol: { span: 4 }, wrapperCol: { span: 14 } } : null;

  return (
    <Form
      {...formItemLayout}
      layout={formLayout}
      form={form}
      initialValues={{ layout: formLayout }}
      onValuesChange={onFormLayoutChange}
      style={{ maxWidth: 600 }}
    >
      <Form.Item label="Comment">
        <Input placeholder="Add comment message..." value={comment} onChange={e => setComment(e.target.value)} />
      </Form.Item>
    </Form>
  );
}
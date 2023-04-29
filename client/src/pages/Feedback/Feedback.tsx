import { Breadcrumb, Card, Divider, Button, Form, Input, Select, message } from 'antd';
const { Option } = Select;
import { useAppDispatch, useAppSelector } from "../../store/hooks";
import { shareFeedback } from '../../store/feedback/feedbackSlice';

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

/* eslint-disable no-template-curly-in-string */
const validateMessages = {
  required: '${label} is required!',
};
/* eslint-enable no-template-curly-in-string */

const Suggestions: React.FC = () => {
  const user = useAppSelector((state) => state.user.user);
  const [messageApi, contextHolder] = message.useMessage();

  const success = (message: string) => {
    messageApi.open({
      type: 'success',
      content: message,
    });
  };

  const error = (message: string) => {
    messageApi.open({
      type: 'error',
      content: message,
    });
  };

  const dispatch = useAppDispatch();

  const onFinish = (values: any) => {
    const data = {...values, author: user.login, authorImg: user.avatar_url};
    dispatch(shareFeedback(data)).then(action => {
      // console.log(action);
      if (action.type === 'feedback/shareFeedback/fulfilled') {
        success(action.payload);
      } else if (action.type === 'feedback/shareFeedback/rejected') {
        error(action.payload.message);
      }
    });
  };

  return (
    <div>
      {contextHolder}

      <Breadcrumb
        style={{
          margin: '16px 0',
        }}
      >
        <Breadcrumb.Item>Feedback</Breadcrumb.Item>
      </Breadcrumb>

      <Divider />

      <Card style={{marginBottom: "15px"}}>
        <p>At AppRepo, we strive to provide the best experience for our users. We welcome all feedback and suggestions to help us improve our platform. Please let us know if you encounter any issues, or if you have any ideas for how we can enhance our service. Your input is valuable to us and we appreciate your contribution to making our platform better.</p>
      </Card>

      <Card>
        <Form
          {...layout}
          name="nest-messages"
          onFinish={onFinish}
          style={{ maxWidth: 600 }}
          validateMessages={validateMessages}
        >
          <Form.Item
            name="area"
            label="Area"
            rules={[
              {
                required: true,
              },
            ]}
          >
            <Select
              placeholder="Select a area"
              allowClear
            >
              <Option value="SUGGESTION">Suggestion</Option>
              <Option value="ISSUE">Issue</Option>
              <Option value="OTHER">Other</Option>
            </Select>
          </Form.Item>
          <Form.Item name={['message']} label="Message"  rules={[{ required: true }]}>
            <Input.TextArea />
          </Form.Item>
          <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 8 }}>
            <Button type="primary" htmlType="submit">
              Submit
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  )
}

export default Suggestions;
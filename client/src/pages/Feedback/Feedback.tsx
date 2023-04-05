import { Breadcrumb, Card, Divider, Button, Form, Input, Select, InputNumber, Space } from 'antd';
const { Option } = Select;

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

/* eslint-disable no-template-curly-in-string */
const validateMessages = {
  required: '${label} is required!',
};
/* eslint-enable no-template-curly-in-string */

const onFinish = (values: any) => {
  console.log(values);
};

const Suggestions: React.FC = () => {
  return (
    <div>
      <Breadcrumb
        style={{
          margin: '16px 0',
        }}
      >
        <Breadcrumb.Item>Suggestions</Breadcrumb.Item>
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
              <Option value="Suggestion">Suggestion</Option>
              <Option value="Issue">Issue</Option>
              <Option value="Other">Other</Option>
            </Select>
          </Form.Item>
          <Form.Item name={['comment']} label="Comment"  rules={[{ required: true }]}>
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
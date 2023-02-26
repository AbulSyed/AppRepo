import React, { useState } from 'react';
import { Button, message, Form, Modal, Select } from 'antd';
const { Option } = Select;
import { shareRepo } from '../store/repo/repoSlice';
import { useAppDispatch } from '../store/hooks';

interface Values {
  title: string;
  description: string;
  modifier: string;
}

interface CollectionCreateFormProps {
  open: boolean;
  onCreate: (values: Values) => void;
  onCancel: () => void;
  name: string;
}

const CollectionCreateForm: React.FC<CollectionCreateFormProps> = ({
  open,
  onCreate,
  onCancel,
  name
}) => {
  const [form] = Form.useForm();

  return (
    <Modal
      open={open}
      title={`Which category which you like to share ${name}?`}
      okText="Create"
      cancelText="Cancel"
      onCancel={onCancel}
      onOk={() => {
        form
          .validateFields()
          .then((values) => {
            form.resetFields();
            onCreate(values);
          })
          .catch((info) => {
            console.log('Validate Failed:', info);
          });
      }}
    >
      <Form
        form={form}
        layout="vertical"
        name="form_in_modal"
      >
        <Form.Item
          name="category"
          label="Category"
          rules={[
            {
              required: true,
            },
          ]}
        >
          <Select
            placeholder="Select a category"
            allowClear
          >
            <Option value="UIT">UI Template</Option>
            <Option value="API">API</Option>
            <Option value="IAC">IaC</Option>
            <Option value="SCRIPT">Script</Option>
            <Option value="FUNCTION">Function</Option>
          </Select>
        </Form.Item>
      </Form>
    </Modal>
  );
};

type DialogFormProps = {
  type: "link" | "text" | "ghost" | "default" | "primary" | "dashed",
  value: string,
  name: string,
  desc: string,
  lang: string,
  url: string,
  clone_url: string,
}

const DialogForm: React.FC<DialogFormProps> = ({ type, value, name, desc, lang, url, clone_url }) => {
  const [open, setOpen] = useState(false);
  const dispatch = useAppDispatch();

  const [messageApi, contextHolder] = message.useMessage();

  const success = () => {
    messageApi.open({
      type: 'success',
      content: 'Repository shared',
    });
  };

  const error = (message: string) => {
    messageApi.open({
      type: 'error',
      content: message,
    });
  };

  const onCreate = (values: any) => {
    const data = {
      name,
      description: desc,
      html_url: url,
      language: lang,
      clone_url,
      category: values.category
    }

    dispatch(shareRepo(data)).then((action) => {
      console.log(action)
      if (action.type === 'repo/shareRepo/fulfilled') {
        console.log('Request passed:', action.payload);
        success();
      } else if (action.type === 'repo/shareRepo/rejected') {
        console.log('Request failed:', action.payload.message);
        error(action.payload.message);
      }
    });
    // console.log('Received values of form: ', values);
    setOpen(false);
  };

  return (
    <div>
      {contextHolder}
      <Button
        type={type}
        onClick={() => {
          setOpen(true);
        }}
      >
        {value}
      </Button>
      <CollectionCreateForm
        open={open}
        onCreate={onCreate}
        onCancel={() => {
          setOpen(false);
        }}
        name={name}
      />
    </div>
  );
};

export default DialogForm;
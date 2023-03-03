import React, { useState } from 'react';
import { Button, message, Form, Modal, Select } from 'antd';
const { Option } = Select;
import { shareRepo } from '../store/repo/repoSlice';
import { useAppDispatch } from '../store/hooks';
import TechTag from './TechTag';

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
  lang?: string;
  tags: string[];
  setTags: any;
}

const CollectionCreateForm: React.FC<CollectionCreateFormProps> = ({
  open,
  onCreate,
  onCancel,
  name,
  lang,
  tags,
  setTags
}) => {
  const [form] = Form.useForm();
  // had to push a component down again
  // const [tags, setTags] = useState([]);

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
        Technologies:<br/>
        <TechTag tags={tags} setTags={setTags} lang={lang} />
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
  const [tags, setTags] = useState([]);

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
      category: values.category,
      tech: tags
    }

    dispatch(shareRepo(data)).then((action) => {
      if (action.type === 'repo/shareRepo/fulfilled') {
        console.log('Request passed:', action.payload);
        success();
      } else if (action.type === 'repo/shareRepo/rejected') {
        console.log('Request failed:', action.payload.message);
        error(action.payload.message);
      }
    });
    // console.log('Received values of form: ', values);
    // console.log('Received tags of form: ', tags);
    // console.log('data: ', data);
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
        lang={lang}
        tags={tags} setTags={setTags}
      />
    </div>
  );
};

export default DialogForm;
import { Breadcrumb, Divider } from "antd";

const Admin: React.FC = () => {

  return (
    <div>
      <Breadcrumb
        style={{
          margin: '16px 0',
        }}
      >
        <Breadcrumb.Item>Admin</Breadcrumb.Item>
      </Breadcrumb>

      <Divider />
    </div>
  )
}

export default Admin;
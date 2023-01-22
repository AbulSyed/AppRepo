import { Breadcrumb, Divider, Empty } from 'antd'

const MyRepos: React.FC = () => {
  return (
    <div>
      <Breadcrumb
        style={{
          margin: '16px 0',
        }}
      >
        <Breadcrumb.Item>My repos</Breadcrumb.Item>
      </Breadcrumb>

      <Divider />

      <Empty
        image={Empty.PRESENTED_IMAGE_SIMPLE}
        description={
          <span>Nothing in my repos</span>
        }
      >
      </Empty>
    </div>
  )
}

export default MyRepos;
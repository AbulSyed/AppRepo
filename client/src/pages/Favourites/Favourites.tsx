import { Breadcrumb, Divider, Empty } from 'antd'

const Favourites: React.FC = () => {
  return (
    <div>
      <Breadcrumb
        style={{
          margin: '16px 0',
        }}
      >
        <Breadcrumb.Item>Favourites</Breadcrumb.Item>
      </Breadcrumb>

      <Divider />

      <Empty
        image={Empty.PRESENTED_IMAGE_SIMPLE}
        description={
          <span>Nothing in favourites</span>
        }
      >
      </Empty>
    </div>
  )
}

export default Favourites;
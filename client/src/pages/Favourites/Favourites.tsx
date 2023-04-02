import { Breadcrumb, Divider, Empty } from 'antd'
import { useAppSelector } from "../../store/hooks";

const Favourites: React.FC = () => {
  const starredRepos = useAppSelector((state) => state.repo.starredRepos);

  console.log(starredRepos)

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
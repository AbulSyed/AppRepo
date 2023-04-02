import { Breadcrumb, Divider, Empty } from 'antd'
import CategoryTable from '../../components/CategoryTable';
import { useAppSelector } from "../../store/hooks";

const Favourites: React.FC = () => {
  // const starredRepos = useAppSelector((state) => state.repo.starredRepos);
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);

  // filtering sharedRepos for items that have been 'starred'
  let starredRepos = [];
  starredRepos = sharedRepos.filter(obj => obj.starred);

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

      {
      // issue with the 'star' feature not in sync
      // starredRepos ?
      //   Object.keys(starredRepos).map(key => {
      //     return (
      //       <div key={key}>
      //         <Header title={key} />
      //         <CategoryTable sharedRepos={starredRepos[key]} />
      //       </div>
      //     )
      //   })
      starredRepos ?
        <CategoryTable sharedRepos={starredRepos} />
        :
        <Empty
          image={Empty.PRESENTED_IMAGE_SIMPLE}
          description={
            <span>Nothing in favourites</span>
          }
        >
        </Empty>
      }
    </div>
  )
}

export default Favourites;
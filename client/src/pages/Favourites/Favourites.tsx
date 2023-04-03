import { Breadcrumb, Divider, Empty } from 'antd'
import CategoryTable from '../../components/CategoryTable';
import { useAppSelector } from "../../store/hooks";
import Header from './Header';

const Favourites: React.FC = () => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);

  // filtering sharedRepos for items that have been 'starred'
  let starredRepos = [];
  starredRepos = sharedRepos.filter(obj => obj.starred);

  // map containing category as 'key' and list of repos as 'value'
  function getCategoryRepoMap(starredRepos: any) {
    const map: any = {};
  
    for (const repoDto of starredRepos) {
      const tempKey = repoDto.category;
      
      if (!map[tempKey]) {
        map[tempKey] = [];
      }
      
      map[tempKey].push(repoDto);
    }
    
    return map;
  }

  const starredReposMap = getCategoryRepoMap(starredRepos);

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
      Object.keys(starredReposMap).length != 0 ?
        Object.keys(starredReposMap).map(key => {
          return (
            <div key={key}>
              <Header title={key} />
              <CategoryTable sharedRepos={starredReposMap[key]} />
            </div>
          )
        })
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
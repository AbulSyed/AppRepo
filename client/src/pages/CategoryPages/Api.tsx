import CategoryTable from '../../components/CategoryTable';
import { useAppSelector } from '../../store/hooks';

const Api: React.FC = () => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);

  // filtering by category
  let filteredCategory = [];
  filteredCategory = sharedRepos.filter(obj => obj.category == "API");

  return (
    <div>
      <CategoryTable sharedRepos={filteredCategory} />
    </div>
  )
}

export default Api;
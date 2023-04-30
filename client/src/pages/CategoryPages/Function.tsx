import CategoryTable from '../../components/CategoryTable';
import { useAppSelector } from '../../store/hooks';

const Function: React.FC = () => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);

  // filtering by category
  let filteredCategory = [];
  filteredCategory = sharedRepos.filter(obj => obj.category == "FUNCTION");

  return (
    <div>
      <CategoryTable sharedRepos={filteredCategory} />
    </div>
  )
}

export default Function;
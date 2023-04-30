import CategoryTable from '../../components/CategoryTable';
import { useAppSelector } from '../../store/hooks';

const Script: React.FC = () => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);

  // filtering by category
  let filteredCategory = [];
  filteredCategory = sharedRepos.filter(obj => obj.category == "SCRIPT");

  return (
    <div>
      <CategoryTable sharedRepos={filteredCategory} />
    </div>
  )
}

export default Script;
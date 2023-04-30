import CategoryTable from '../../components/CategoryTable';
import { useAppSelector } from '../../store/hooks';

const Iac: React.FC = () => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);

  // filtering by category
  let filteredCategory = [];
  filteredCategory = sharedRepos.filter(obj => obj.category == "IAC");

  return (
    <div>
      <CategoryTable sharedRepos={filteredCategory} />
    </div>
  )
}

export default Iac;
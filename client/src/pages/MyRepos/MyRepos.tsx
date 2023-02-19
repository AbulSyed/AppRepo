import { Breadcrumb, Col, Divider, Empty, Row } from 'antd'
import RepoCard from '../../components/RepoCard';
import { useAppSelector } from '../../store/hooks';

const MyRepos: React.FC = () => {
  const repo = useAppSelector((state) => state.repo.repo);

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

      <Row gutter={[16, 24]}>
        {
          repo ? (
            repo.map(r => (
              <Col xs={24} sm={12} lg={8} key={r.html_url}>
                <RepoCard name={r.name} desc={r.description} lang={r.language} url={r.html_url} clone_url={r.clone_url} />
              </Col>
            ))
          ) : 
          <Empty
            image={Empty.PRESENTED_IMAGE_SIMPLE}
            description={
              <span>Nothing in my repos</span>
            }
          >
          </Empty>
        }
      </Row>
    </div>
  )
}

export default MyRepos;
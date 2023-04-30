import './Home.scss'
import { Breadcrumb, Card, Col, Divider, Row, Typography } from 'antd';
import { Link } from 'react-router-dom';
const { Title } = Typography;

const Home: React.FC = () => {
  return (
    <div>
      <Breadcrumb
        style={{
          margin: '16px 0',
        }}
      >
        <Breadcrumb.Item>Home</Breadcrumb.Item>
      </Breadcrumb>

      <Divider />
      
      {/* <p>{import.meta.env.VITE_SOME_KEY}</p> */}

      <Typography>
        <Title level={3}>What are you looking for?</Title>
        <Row gutter={[16, 24]}>
            <Col xs={24} sm={12} md={8}>
              <Link to={'/uit'}>
                <Card className='my-card'>UI Templates</Card>
              </Link>
            </Col>
            <Col xs={24} sm={12} md={8}>
              <Link to={'/api'}>
                <Card className='my-card'>APIs</Card>
              </Link>
            </Col>
            <Col xs={24} sm={12} md={8}>
              <Link to={'/iac'}>
                <Card className='my-card'>IaC</Card>
              </Link>
            </Col>
            <Col xs={24} sm={12} md={8}>
              <Link to={'/scripts'}>
                <Card className='my-card'>Scripts</Card>
              </Link>
            </Col>
            <Col xs={24} sm={12} md={8}>
              <Link to={'/functions'}>
                <Card className='my-card'>Useful functions</Card>
              </Link>
            </Col>
        </Row>
      </Typography>
    </div>
  )
}

export default Home;
import { Typography } from 'antd'
const { Title } = Typography;
import {
  GithubOutlined
} from '@ant-design/icons';
import './Auth.scss'
import logo from '../../assets/logo.png';

const Auth: React.FC = () => {
  const authorize = () => {
    // using GitHub as auth server
    // https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps
    // below link will navigate user to GitHub signin page
    // then on backend we need to retrieve code and exchange for access token
    window.location.href='https://github.com/login/oauth/authorize?client_id=e7913495da0b920cfa5c'
  }

  return (
    <div>
      <img src={logo} style={{display: 'block', margin: '0 auto'}} alt="" />
      <Typography>
        <Title level={5} style={{textAlign: 'center', margin: '0 0 50px 0', color: '#021529'}}>Login using GitHub</Title>
      </Typography>
      <GithubOutlined style={{fontSize: '100px', display: 'block', cursor: 'pointer'}} className="github-icon" onClick={authorize} />
    </div>
  )
}

export default Auth;
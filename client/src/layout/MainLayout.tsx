import React, { useEffect, useState } from 'react';
import { Routes, Route, useNavigate, Navigate } from "react-router-dom";
import {
  HomeOutlined,
  StarOutlined,
  UserOutlined,
} from '@ant-design/icons';
import { Affix, Layout, Menu, theme } from 'antd';
import logo2 from '../assets/logo2.png';
import Home from '../pages/home/Home';
import NotFound from '../pages/NotFound/NotFound';
import Favourites from '../pages/Favourites/Favourites';
import MyRepos from '../pages/MyRepos/MyRepos';
import Auth from '../pages/Auth/Auth';
import Cookies from 'js-cookie';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { fetchUser } from '../store/user/userSlice';
import ServerError from '../pages/ServerError/ServerError';
import { fetchRepos } from '../store/repo/repoSlice';
import { fetchSharedRepos } from '../store/repo/repoSlice';

const { Header, Content, Sider } = Layout;

const items = [
  {
    key: '/',
    icon: <HomeOutlined />,
    label: 'Home',
  },
  {
    key: '/myrepos',
    icon: <UserOutlined />,
    label: 'My repos',
  },
  {
    key: '/favourites',
    icon: <StarOutlined />,
    label: 'Favourites',
  },
];

const MainLayout: React.FC = () => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  const navigate = useNavigate();
  // cookie sent from backend
  const cookie = Cookies.get('loggedIn');
  const dispatch = useAppDispatch();
  const user = useAppSelector((state) => state.user.user);

  useEffect(() => {
    if (cookie) {
      dispatch(fetchUser(cookie));
      dispatch(fetchRepos(cookie));
      dispatch(fetchSharedRepos());
    }
  }, []);

  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      {
        // only show sidebar if user is logged in
        cookie && (
          <Affix offsetTop={0} style={{ height: '100%', overflow: 'auto' }}>
            <Sider
              breakpoint="md"
              collapsible
              collapsed={collapsed}
              onCollapse={(value) => setCollapsed(value)} style={{
              height: '100vh',
            }}>
              <div
                style={{
                  height: 32,
                  marginTop: 30,
                  display: 'flex',
                  justifyContent: 'center',
                  alignItems: 'center',
                }}
              >
                <img src={logo2} style={{display: 'block', margin: '0 auto'}} alt="" /></div>
              <Menu
                theme="dark"
                defaultSelectedKeys={[window.location.pathname]}
                mode="inline"
                items={items}
                onClick={(item) => navigate(item.key)}
              />
            </Sider>
          </Affix>
        )
      }
      <Layout className="site-layout">
        <Header
          style={{
            padding: 0,
            background: colorBgContainer,
            position: 'sticky', top: 0, zIndex: 1, width: '100%'
          }}
        >
          <div style={{display: 'flex', justifyContent: 'space-between'}}>
            {
              cookie && 
              (
                <>
                  <h1 style={{marginLeft: '20px'}}>Welcome {user ? user.login : ''}</h1>
                  {
                    user ? 
                    <img src={user.avatar_url} style={{height: '45px', borderRadius: '5px', display: 'block', margin: '10px 15px'}}  /> : 
                    <img src='https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png' style={{height: '45px', borderRadius: '5px', display: 'block', margin: '10px 15px'}}  />
                  }
                </>
              )
            }
          </div>
        </Header>
        <Content style={{ margin: '0 16px' }}>
          <Routes>
            <Route path="/" element={cookie ? <Home /> : <Navigate to="/auth" />} />
            <Route path="/myrepos" element={cookie ? <MyRepos /> : <Navigate to="/auth" />} />
            <Route path="/favourites" element={cookie ? <Favourites /> : <Navigate to="/auth" />} />
            <Route path="/auth" element={!cookie ? <Auth /> : <Navigate to="/" />} />
            <Route path="/500" element={!cookie ? <ServerError /> : <Navigate to="/" />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Content>
      </Layout>
    </Layout>
  );
};

export default MainLayout;
import React, { useEffect, useState } from 'react';
import { Routes, Route, useNavigate, Navigate } from "react-router-dom";
import {
  HomeOutlined,
  StarOutlined,
  UserOutlined,
  SolutionOutlined,
  IdcardOutlined,
} from '@ant-design/icons';
import { Affix, Layout, Menu, theme } from 'antd';
import logo2 from '../assets/logo2.png';
import Home from '../pages/home/Home';
import NotFound from '../pages/NotFound/NotFound';
import Favourites from '../pages/Favourites/Favourites';
import MyRepos from '../pages/MyRepos/MyRepos';
import Uit from '../pages/CategoryPages/Uit';
import Auth from '../pages/Auth/Auth';
import Cookies from 'js-cookie';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { fetchUser, fetchUserByToken } from '../store/user/userSlice';
import ServerError from '../pages/ServerError/ServerError';
import { fetchRepos } from '../store/repo/repoSlice';
import { fetchSharedRepos, fetchStarredRepos } from '../store/repo/repoSlice';
import Feedback from '../pages/Feedback/Feedback';
import Admin from '../pages/Admin/Admin';
import { getFeedback } from "../store/feedback/feedbackSlice";

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
  {
    key: '/feedback',
    icon: <SolutionOutlined />,
    label: 'Feedback',
  },
  {
    key: '/admin',
    icon: <IdcardOutlined />,
    label: 'Admin',
  },
];

const MainLayout: React.FC = () => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  const navigate = useNavigate();
  // cookie sent from backend - contains GitHub auth token
  const token = Cookies.get('token');
  const dispatch = useAppDispatch();
  const user = useAppSelector((state) => state.user.user);

  const showLastObject = (items: any, isAdmin: boolean) => {
    if (isAdmin) {
      return items;
    } else {
      return items.filter((el: { label: string; }) => el.label !== "Admin");
    }
  }
  
  useEffect(() => {
    if (token) {
      // dispatch(fetchUser(token));
      dispatch(fetchUserByToken(token));
    }
  }, []);

  useEffect(() => {
    if (user.login.length > 0 && token) {
      dispatch(fetchRepos({name: user.login, token}));
      dispatch(fetchSharedRepos(user.login));
      dispatch(fetchStarredRepos(user.login));
    }
  }, [user]);

  useEffect(() => {
    if (user.admin) {
      dispatch(getFeedback());
    }
  }, [user])

  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      {
        // only show sidebar if user is logged in
        token && (
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
                items={showLastObject(items, user.admin)}
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
              token && 
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
            <Route path="/" element={token ? <Home /> : <Navigate to="/auth" />} />
            {/* CATEGORY PAGES */}
            <Route path="/uit" element={token ? <Uit /> : <Navigate to="/auth" />} />
            {/* CATEGORY PAGES */}
            <Route path="/myrepos" element={token ? <MyRepos /> : <Navigate to="/auth" />} />
            <Route path="/favourites" element={token ? <Favourites /> : <Navigate to="/auth" />} />
            <Route path="/feedback" element={token ? <Feedback /> : <Navigate to="/auth" />} />
            {
              user.admin && 
              <Route path="/admin" element={token ? <Admin /> : <Navigate to="/auth" />} />
            }
            <Route path="/auth" element={!token ? <Auth /> : <Navigate to="/" />} />
            <Route path="/500" element={!token ? <ServerError /> : <Navigate to="/" />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Content>
      </Layout>
    </Layout>
  );
};

export default MainLayout;
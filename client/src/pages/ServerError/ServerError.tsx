import { Button, Result } from 'antd';

const ServerError: React.FC = () => {
  const retryLogin = () => {
    window.location.href='https://github.com/login/oauth/authorize?client_id=e7913495da0b920cfa5c'
  }

  return (
    <Result
      status="500"
      title="500"
      subTitle="Sorry, Something went wrong."
      extra={<Button type="primary" onClick={retryLogin}>Retry</Button>}
    />
  )
};

export default ServerError;
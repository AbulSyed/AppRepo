import { Card } from "antd";
import { GithubOutlined } from '@ant-design/icons';
import DialogForm from './DialogForm';

type RepoCardProps = {
  name: string,
  desc: string,
  lang: string,
  url: string,
  clone_url: string
}

const RepoCard: React.FC<RepoCardProps> = ({name, url, desc, lang, clone_url}) => {
  return (
    <Card
      title={name}
      bordered={false}
      extra={<DialogForm type="link" value="share" name={name} desc={desc} lang={lang} url={url} clone_url={clone_url} />}
      style={{
        width: 300,
      }}
      actions={[
        <a href={url} target="_blank"><GithubOutlined key="github" /></a>,
      ]}
    >
      <p>{desc}</p>
      <p>{lang}</p>
    </Card>
  )
}

export default RepoCard;
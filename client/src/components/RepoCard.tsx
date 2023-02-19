import { Card } from "antd";
import { GithubOutlined } from '@ant-design/icons';

type RepoCardProps = {
  name: string,
  desc: string,
  lang: string,
  url: string,
  clone_url: string
}

const RepoCard: React.FC<RepoCardProps> = ({name, url, desc, lang}: RepoCardProps) => {
  return (
    <Card
      title={name}
      bordered={false}
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
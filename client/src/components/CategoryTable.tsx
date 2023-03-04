import React, { useState } from 'react';
import { useAppSelector } from "../store/hooks";
import { Button, Table, Tag } from 'antd';
import { GithubOutlined, StarOutlined } from '@ant-design/icons';

const { Column } = Table;

interface DataType {
  key: React.Key;
  name: string;
  description: string;
  html_url: string;
  tech: string[];
  starred: boolean;
}

const data: DataType[] = [
  {
    key: '1',
    name: 'John',
    description: 'Brown',
    html_url: 'https://github.com/AbulSyed/Data-vis',
    tech: ['nice', 'developer', 'spring boot'],
    starred: false,
  },
  {
    key: '2',
    name: 'Jim',
    description: 'Green',
    html_url: 'London No. 1 Lake Park',
    tech: ['loser'],
    starred: true,
  },
];

interface CategoryTableProps {
  category: string
}

const CategoryTable: React.FC<CategoryTableProps> = ({ category }) => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);
  const [dummyState, setDummyState] = useState(new Date());

  console.log('sharedRepos===', sharedRepos);

  // filtering by category
  let fileredCategory = [];
  fileredCategory = sharedRepos.filter(obj => {
    return obj.category == category;
  })

  console.log('fileredCategory ===', fileredCategory)

  const handleFavourite = (record: DataType) => {
    record.starred = !record.starred;
    // force a re-render of component, as updating the starred property in the data array alone won't trigger a re-render
    // as React only re-renders a component when its state or props change
    setDummyState(new Date());
  }

  return (
    <Table dataSource={data} style={{ marginTop: '2rem' }}>
      <Column title="Name" dataIndex="name" key="name" />
      <Column title="Description" dataIndex="description" key="description" />
      <Column
        title="Link"
        key="html_url"
        render={(_: any, record: DataType) => (
          <Button href={record.html_url} target="_blank" icon={<GithubOutlined />} />
        )}
      />
      <Column
        title="Tech"
        dataIndex="tech"
        key="tech"
        render={(tech: string[]) => (
          <>
            {tech.map((tag) => (
              <Tag color="blue" key={tag}>
                {tag}
              </Tag>
            ))}
          </>
        )}
      />
      <Column
        title="Star"
        key="star"
        render={(_: any, record: DataType) => (
          <Button onClick={() => handleFavourite(record)} style={{ color: record.starred ? "gold" : "grey", borderColor: record.starred ? "gold" : "white" }} icon={<StarOutlined />} />
        )}
      />
    </Table>
  )
}

export default CategoryTable;
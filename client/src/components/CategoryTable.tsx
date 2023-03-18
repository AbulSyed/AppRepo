import React, { useState } from 'react';
import { useAppSelector } from "../store/hooks";
import { useAppDispatch } from '../store/hooks';
import { star } from '../store/repo/repoSlice';
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

interface CategoryTableProps {
  category: string
}

const CategoryTable: React.FC<CategoryTableProps> = ({ category }) => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);
  const [dummyState, setDummyState] = useState(new Date());
  const dispatch = useAppDispatch();

  // console.log('sharedRepos===', sharedRepos);

  // filtering by category
  let filteredCategory = [];
  filteredCategory = sharedRepos.filter(obj => obj.category == category);

  // console.log('filteredCategory===', filteredCategory)
  // console.table(filteredCategory)

  const handleStar = (record: DataType) => {
    const newRecord = { ...record, starred: !record.starred };
    dispatch(star(newRecord));

    // force a re-render of component, as updating the starred property in the data array alone won't trigger a re-render
    // as React only re-renders a component when its state or props change
    setDummyState(new Date());
  }

  return (
    <Table dataSource={filteredCategory} style={{ marginTop: '2rem' }}>
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
          <Button onClick={() => handleStar(record)} style={{ color: record.starred ? "gold" : "grey", borderColor: record.starred ? "gold" : "white" }} icon={<StarOutlined />} />
        )}
      />
    </Table>
  )
}

export default CategoryTable;
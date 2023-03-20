import React, { useState } from 'react';
import { useAppSelector } from "../store/hooks";
import { useAppDispatch } from '../store/hooks';
import { star, starRepo } from '../store/repo/repoSlice';
import { Button, message, Table, Tag } from 'antd';
import { GithubOutlined, StarOutlined } from '@ant-design/icons';

const { Column } = Table;

interface DataType {
  key: React.Key;
  name: string;
  description: string;
  html_url: string;
  tech: string[];
  starred: boolean;
  id: number
}

interface CategoryTableProps {
  category: string
}

const CategoryTable: React.FC<CategoryTableProps> = ({ category }) => {
  const sharedRepos = useAppSelector((state) => state.repo.sharedRepos);
  const [dummyState, setDummyState] = useState(new Date());
  const state = useAppSelector(state => state.user);
  const dispatch = useAppDispatch();

  const [messageApi, contextHolder] = message.useMessage();

  const success = (message: string) => {
    messageApi.open({
      type: 'success',
      content: message,
    });
  };

  const error = (message: string) => {
    messageApi.open({
      type: 'error',
      content: message,
    });
  };

  // console.log('sharedRepos===', sharedRepos);

  // filtering by category
  let filteredCategory = [];
  filteredCategory = sharedRepos.filter(obj => obj.category == category);

  // console.log('filteredCategory===', filteredCategory)
  // console.table(filteredCategory)

  const handleStar = (record: DataType) => {
    const newRecord = { ...record, starred: !record.starred };
    // updates boolean star state
    dispatch(star(newRecord));
    // API request
    dispatch(starRepo({
      starredBy: state.user.login,
      repoId: record.id
    })).then(action => {
      console.log(action.payload);
      if (action.type === 'repo/starRepo/fulfilled') {
        success(action.payload);
      } else if (action.type === 'repo/starRepo/rejected') {
        error(action.payload.message);
      }
    });
  }

  return (
    <>
      {contextHolder}
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
    </>
  )
}

export default CategoryTable;
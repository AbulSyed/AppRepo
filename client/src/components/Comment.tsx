import { format, parseISO } from 'date-fns';

interface CommentProps {
  data: {
    id: number;
    author: string;
    dateTime: string;
    message: string
  }
}

const Comment: React.FC<CommentProps> = ({ data }) => {
  return (
    <div style={{ padding: '.3rem', maxWidth: '20rem', borderBottom: "1px solid #dbdbcf" }}>
      <div style={{ color: 'grey' }}>
        <span style={{ fontSize: "13px" }}>{data.author}</span>
        <span style={{ fontSize: "9px", marginLeft: ".2rem" }}>{format(parseISO(data.dateTime), 'dd/MM/yyyy')}</span>
      </div>
      <div>{data.message}</div>
    </div>
  )
}

export default Comment;
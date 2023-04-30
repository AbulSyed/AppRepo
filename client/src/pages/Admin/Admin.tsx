import { Breadcrumb, Divider } from "antd";
import { useAppSelector, useAppDispatch } from "../../store/hooks";
import AdminList from "./AdminList";

const Admin: React.FC = () => {
  const dispatch = useAppDispatch();
  const feedback = useAppSelector(state => state.feedback.feedback);

  return (
    <div>
      <Breadcrumb
        style={{
          margin: '16px 0',
        }}
      >
        <Breadcrumb.Item>Admin</Breadcrumb.Item>
      </Breadcrumb>

      <Divider />

      {(feedback.ISSUE && feedback['ISSUE'].length > 0) && <h1>ISSUE</h1>}
      {(feedback.ISSUE && feedback['ISSUE'].length > 0) && <AdminList data={feedback['ISSUE']} />}

      {(feedback.SUGGESTION && feedback['SUGGESTION'].length > 0) && <h1>SUGGESTION</h1>}
      {(feedback.SUGGESTION && feedback['SUGGESTION'].length > 0) && <AdminList data={feedback['SUGGESTION']} />}

      {(feedback.OTHER && feedback['OTHER'].length > 0) && <h1>OTHER</h1>}
      {(feedback.OTHER && feedback['OTHER'].length > 0) && <AdminList data={feedback['OTHER']} />}

      {/* <h1>ISSUE</h1>
      {(feedback.ISSUE && feedback['ISSUE'].length > 0) && (
        feedback['ISSUE'].map(el => {
          return (
            <AdminList key={el.id} message={el.message} date={el.dateTime} data={el} />
          )
        })
      )}

      <h1>SUGGESTION</h1>
      {(feedback.SUGGESTION && feedback['SUGGESTION'].length > 0) && (
        feedback['SUGGESTION'].map(el => {
          return (
            <AdminList key={el.id} message={el.message} date={el.dateTime} />
          )
        })
      )}

      <h1>OTHER</h1>
      {(feedback.OTHER && feedback['OTHER'].length > 0) && (
        feedback['OTHER'].map(el => {
          return (
            <AdminList key={el.id} message={el.message} date={el.dateTime} />
          )
        })
      )} */}
    </div>
  )
}

export default Admin;
import { useEffect } from "react";
import { useAppSelector, useAppDispatch } from "../hooks";
import { fetchUser } from "./userSlice";

const UserView: React.FC = () => {
  const dispatch = useAppDispatch();
  const user = useAppSelector((state) => state.user);

  useEffect(() => {
    dispatch(fetchUser());
  }, []);

  return (
    <div>
      <h2>user</h2>
      {user.loading && <div>loading...</div>}
      {user.error && <div>{user.error}</div>}
      {user.user && <div>{user.user.login}</div>}
    </div>
  );
};

export default UserView;
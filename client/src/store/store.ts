import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./user/userSlice";
import repoReducer from "./repo/repoSlice";

const store = configureStore({
  reducer: {
    user: userReducer,
    repo: repoReducer,
  },
});

export default store;
// to type useSelector & useDispatch
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
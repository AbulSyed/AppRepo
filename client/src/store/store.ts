import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./user/userSlice";
import repoReducer from "./repo/repoSlice";
import feedbackReducer from "./feedback/feedbackSlice";

const store = configureStore({
  reducer: {
    user: userReducer,
    repo: repoReducer,
    feedback: feedbackReducer,
  },
});

export default store;
// to type useSelector & useDispatch
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
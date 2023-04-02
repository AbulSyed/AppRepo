import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../api/api";

type User = {
  login: string,
  avatar_url: string,
  html_url: string,
  repos_url: string
}

type InitialState = {
  loading: boolean,
  user: User,
  error: string
}

const initialState: InitialState = {
  loading: false,
  user: {
    login: '',
    avatar_url: '',
    html_url: '',
    repos_url: ''
  },
  error: '',
}

export const fetchUser = createAsyncThunk("user/fetchUser", (name: string) => {
  return api
    .get(`/authservice/getUser/${name}`)
    // .then((res) => console.log(res.data))
    .then((res) => res.data);
});

export const fetchUserByToken = createAsyncThunk("repo/fetchUserByToken", async (token: string, thunkAPI: any) => {
  try {
    const res = await api.post(`/authservice/getUser`, {
      token
    });
    return res.data;
  } catch(err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchUser.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(fetchUser.fulfilled, (state, action) => {
      state.loading = false;
      state.user = action.payload;
      state.error = '';
    });
    builder.addCase(fetchUser.rejected, (state, action) => {
      state.loading = false;
      state.user = {
        login: '',
        avatar_url: '',
        html_url: '',
        repos_url: ''
      };
      state.error = action.error.message || "Something went wrong...";
    });
    builder.addCase(fetchUserByToken.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(fetchUserByToken.fulfilled, (state, action) => {
      state.loading = false;
      state.user = action.payload;
      state.error = '';
    });
    builder.addCase(fetchUserByToken.rejected, (state, action) => {
      state.loading = false;
      state.user = {
        login: '',
        avatar_url: '',
        html_url: '',
        repos_url: ''
      };
      state.error = action.error.message || "Something went wrong...";
    });
  },
});

export default userSlice.reducer;
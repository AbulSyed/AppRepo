import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../api/api";

type Repo = {
  name: string,
  html_url: string,
  description: string,
  clone_url: '',
  language: string
}[]

type InitialState = {
  loading: boolean,
  repo: Repo,
  error: string
}

const initialState: InitialState = {
  loading: false,
  repo: [{
    name: '',
    html_url: '',
    description: '',
    clone_url: '',
    language: ''
  }],
  error: '',
}

export const fetchRepos = createAsyncThunk("repo/fetchRepos", (name: string) => {
  return api
    .get(`/reposervice/getRepos/${name}`)
    .then((res) => res.data);
});

const repoSlice = createSlice({
  name: "repo",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchRepos.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(fetchRepos.fulfilled, (state, action) => {
      state.loading = false;
      state.repo = action.payload;
      state.error = '';
    });
    builder.addCase(fetchRepos.rejected, (state, action) => {
      state.loading = false;
      state.repo = [];
      state.error = action.error.message || "Something went wrong...";
    });
  },
});

export default repoSlice.reducer;
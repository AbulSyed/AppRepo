import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../api/api";

interface Repo {
  name: string;
  html_url: string;
  description: string;
  clone_url: string;
  language: string;
}

interface SharedRepo extends Repo {
  category: string
}

interface InitialState {
  loading: boolean;
  repos: Repo[];
  sharedRepos: SharedRepo[];
  error: string;
}

const initialState: InitialState = {
  loading: false,
  repos: [],
  sharedRepos: [],
  error: '',
}

export const fetchRepos = createAsyncThunk("repo/fetchRepos", (name: string) => {
  return api
    .get(`/reposervice/getRepos/${name}`)
    .then((res) => res.data);
});

export const fetchSharedRepos = createAsyncThunk("repo/fetchSharedRepos", () => {
  return api
    .get(`/reposervice/getSharedRepos`)
    .then((res) => res.data);
});

export const shareRepo = createAsyncThunk("repo/shareRepo", async (data: SharedRepo, thunkAPI) => {
  try {
    const res = await api.post(`/reposervice/shareRepo`, data);
    return res.data;
  } catch (err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

const repoSlice = createSlice({
  name: "repo",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    // fetching repos
    builder.addCase(fetchRepos.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(fetchRepos.fulfilled, (state, action) => {
      state.loading = false;
      state.repos = action.payload;
      state.error = '';
    });
    builder.addCase(fetchRepos.rejected, (state, action) => {
      state.loading = false;
      state.repos = [];
      state.error = action.error.message || "Something went wrong...";
    });
    // fetching shared repos
    builder.addCase(fetchSharedRepos.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(fetchSharedRepos.fulfilled, (state, action) => {
      state.loading = false;
      state.sharedRepos = action.payload;
      state.error = '';
    });
    builder.addCase(fetchSharedRepos.rejected, (state, action) => {
      state.loading = false;
      state.sharedRepos = [];
      state.error = action.error.message || "Something went wrong...";
    });
    // sharing a repo
    builder.addCase(shareRepo.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(shareRepo.fulfilled, (state, action) => {
      state.loading = false;
      state.sharedRepos.push(action.payload);
      state.error = '';
    });
    builder.addCase(shareRepo.rejected, (state, action) => {
      state.loading = false;
      state.sharedRepos = [];
      state.error = action.error.message || "Something went wrong...";
    });
  },
});

export default repoSlice.reducer;
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
  category: string,
  tech: string[],
  id: number
}

interface StarRepoDto {
  starredBy: string;
  repoId: number;
}

interface StarredRepos {
  [key: string]: {
    name: string;
    html_url: string;
    description: string;
    clone_url: string;
    language: string;
    category: string;
    tech: string[];
    id: number;
  }[];
}

interface InitialState {
  loading: boolean;
  repos: Repo[];
  sharedRepos: SharedRepo[];
  starredRepos: StarredRepos;
  error: string;
}

const initialState: InitialState = {
  loading: false,
  repos: [],
  sharedRepos: [],
  starredRepos: {},
  error: '',
}

// fetches currently logged in users repositories
export const fetchRepos = createAsyncThunk("repo/fetchRepos", async (data: {name: string, token: string | undefined}, thunkAPI: any) => {
  try {
    const res = await api.get(`/reposervice/getRepos/${data.name}`, {
      headers: {
        Authorization: data.token,
      }
    });
    return res.data;
  } catch(err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

// fetches all repositories shared by all users
export const fetchSharedRepos = createAsyncThunk("repo/fetchSharedRepos", async (name: string, thunkAPI: any) => {
  try {
    // username used for logic to check if repos have been starred by current user
    const res = await api.post(`/reposervice/getSharedRepos`, {
      username: name
    });

    // adding key to each object since antd table needs key
    let arr = [];
    arr = res.data.map((obj: { id: any; }) => ({ ...obj, key: obj.id }));

    return arr;
  } catch(err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

// request to share a repo to a category
export const shareRepo = createAsyncThunk("repo/shareRepo", async (data: SharedRepo, thunkAPI: any) => {
  try {
    const res = await api.post(`/reposervice/shareRepo`, data);

    // adding key to object since antd table needs key
    let dataWithKey = {...res.data, key: res.data.id};

    return dataWithKey;
  } catch (err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

// favourite a repo
export const starRepo = createAsyncThunk("repo/starRepo", async (data: StarRepoDto, thunkAPI: any) => {
  try {
    const res = await api.post(`/reposervice/starRepo`, data);

    return res.data;
  } catch (err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

// fetches current users starred repositories
export const fetchStarredRepos = createAsyncThunk("repo/fetchStarredRepos", async (name: string, thunkAPI: any) => {
  try {
    const res = await api.post(`/reposervice/getStarredRepos`, {
      username: name
    });

    // need to add key to each object, as antd
    // iterating over each key
    Object.keys(res.data).forEach((el) => {
      // iterating over each object and adding key prop
      res.data[el].forEach((obj: { key: any; id: any; }) => {
        obj.key = obj.id;
      });
    });

    return res.data;
  } catch(err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

const repoSlice = createSlice({
  name: "repo",
  initialState,
  reducers: {
    star: (state, action) => {
      state.sharedRepos = state.sharedRepos.map(shareRepo => {
        if (shareRepo.id == action.payload.id) {
          return action.payload;
        }
        return shareRepo;
      })
    }
  },
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
    // fetching starred repos
    builder.addCase(fetchStarredRepos.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(fetchStarredRepos.fulfilled, (state, action) => {
      state.loading = false;
      state.starredRepos = action.payload;
      state.error = '';
    });
    builder.addCase(fetchStarredRepos.rejected, (state, action) => {
      state.loading = false;
      state.starredRepos = {};
      state.error = action.error.message || "Something went wrong...";
    });
  },
});

export default repoSlice.reducer;
export const { star } = repoSlice.actions;
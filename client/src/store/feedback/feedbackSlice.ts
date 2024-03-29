import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../api/api";

const initialState: InitialState = {
  loading: false,
  feedback: {
    "ISSUE": [],
    "SUGGESTION": [],
    "OTHER": [],
  },
  error: '',
};

interface InitialState {
  loading: boolean;
  feedback: FeedbackMap;
  error: string;
}

interface FeedbackMap {
  "ISSUE": {
    id: number,
    area: 'ISSUE',
    message: string,
    author: string,
    authorImg: string,
    resolved: boolean,
    comments: [],
    dateTime: string
  }[],
  "SUGGESTION": {
    id: number,
    area: 'SUGGESTION',
    message: string,
    author: string,
    authorImg: string,
    resolved: boolean,
    comments: [],
    dateTime: string
  }[],
  "OTHER": {
    id: number,
    area: 'OTHER',
    message: string,
    author: string,
    authorImg: string,
    resolved: boolean,
    comments: [],
    dateTime: string
  }[],
}

interface Feedback {
  area: string;
  message: string;
}

export const shareFeedback = createAsyncThunk("feedback/shareFeedback", async (data: Feedback, thunkAPI: any) => {
  try {
    const res = await api.post(`/feedbackservice/shareFeedback`, data);

    return res.data;
  } catch (err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

export const getFeedback = createAsyncThunk("feedback/getFeedback", async () => {
  try {
    const res = await api.get(`/feedbackservice/getFeedback`);

    return res.data;
  } catch (err: any) {
    return err.message;
  }
});

export const updateFeedbackResolvedStatusApiRequest = createAsyncThunk("feedback/updateFeedbackResolvedStatus", async (data: any) => {
  try {
    await api.put(`/feedbackservice/feedback/${data.id}`, {
      resolved: !data.resolved
    });
  } catch (err: any) {
    return err.message;
  }
});

export const addComment = createAsyncThunk("feedback/addComment", async (data: any) => {
  try {
    const res = await api.post(`/feedbackservice/addComment/${data.id}`, {
      author: data.author,
      message: data.message
    });

    return {...res.data, area: data.area, postId: data.id};
  } catch (err: any) {
    return err.message;
  }
});

const feedbackSlice = createSlice({
  name: "feedback",
  initialState,
  reducers: {
    updateFeedbackResolvedStatus(state, action) {
      state.feedback[action.payload.area] = state.feedback[action.payload.area].map(feedback => {
        if (feedback.id == action.payload.id) {
          return {
            ...feedback,
            "resolved": !feedback.resolved,
          };
        }
        return feedback;
      })
    }
  },
  extraReducers: (builder) => {
    builder.addCase(getFeedback.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(getFeedback.fulfilled, (state, action) => {
      state.loading = false;
      state.feedback = action.payload;
      state.error = '';
    });
    builder.addCase(getFeedback.rejected, (state, action) => {
      state.loading = false;
      state.feedback = {
        "ISSUE": [],
        "SUGGESTION": [],
        "OTHER": [],
      };
      state.error = action.error.message || "Something went wrong";
    });
    builder.addCase(addComment.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(addComment.fulfilled, (state, action) => {
      state.loading = false;
      state.feedback[action.payload.area].find(el => {
        if (el.id == action.payload.postId) {
          // ideally to clean the payload to match, only required data
          el.comments.push(action.payload);
        }
      })
      state.error = '';
    });
    // builder.addCase(addComment.rejected, (state, action) => {
    //   state.loading = false;
    //   state.feedback = {
    //     "ISSUE": [],
    //     "SUGGESTION": [],
    //     "OTHER": [],
    //   };
    //   state.error = action.error.message || "Something went wrong";
    // });
  },
});

export default feedbackSlice.reducer;
export const { updateFeedbackResolvedStatus } = feedbackSlice.actions;
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../api/api";

const initialState: InitialState = {
  loading: false,
  feedback: {},
  error: '',
};

interface InitialState {
  loading: boolean;
  feedback: {};
  error: string;
}

interface Feedback {
  area: string;
  message: string;
}

export const shareFeedback = createAsyncThunk("feedback/shareFeedback", async (data: Feedback, thunkAPI: any) => {
  try {
    const res = await api.post(`/feedbackservice/shareFeedback`, data);

    console.log(res.data);

    return res.data;
  } catch (err: any) {
    return thunkAPI.rejectWithValue({ message: err.message });
  }
});

const feedbackSlice = createSlice({
  name: "feedback",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    // builder.addCase(shareFeedback.pending, (state) => {
    //   state.loading = true;
    // });
    // builder.addCase(shareFeedback.fulfilled, (state, action) => {
    //   state.loading = false;
    //   state.feedback = action.payload;
    //   state.error = '';
    // });
    // builder.addCase(shareFeedback.rejected, (state, action) => {
    //   state.loading = false;
    //   state.feedback = [];
    //   state.error = action.error.message || "Something went wrong";
    // });
  },
});

export default feedbackSlice.reducer;
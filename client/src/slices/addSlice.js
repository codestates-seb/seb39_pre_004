import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';
const initialPostState = {
  title: '',
  body: '',
};

export const asyncAddFetch = createAsyncThunk(
  'addSlice/fetchAdd',
  async (addRequest) => {
    const res = await axios.post('/questions/add', addRequest);
    try {
      return res.data;
    } catch (error) {
      throw new Error('post요청 에러 발생');
    }
  }
);

const addSlice = createSlice({
  name: 'add',
  initialState: initialPostState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(asyncAddFetch.fulfilled, (state, action) => {
      return action.payload;
    });
  },
});

export default addSlice.reducer;
export const addActions = addSlice.actions;

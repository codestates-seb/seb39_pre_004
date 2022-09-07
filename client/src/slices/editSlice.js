import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const initialPostState = {
  title: '',
  body: '',
};

export const editFetch = createAsyncThunk(
  'editSlice/editFetch',
  async (editRequest) => {
    console.log(editRequest);
    const res = await axios.put(
      editRequest.url,
      { title: editRequest.title, body: editRequest.body },
      {
        headers: { 'Content-Type': 'application/json' },
      }
    );
    try {
      return res.data;
    } catch (error) {
      throw new Error('get요청 에러 발생');
    }
  }
);

const editSlice = createSlice({
  name: 'edit',
  initialState: initialPostState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(editFetch.fulfilled, (state, action) => {
      state.push(action.payload);
    });
  },
});

export default editSlice.reducer;
export const editActions = editSlice.actions;

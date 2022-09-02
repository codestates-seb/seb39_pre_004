import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const initialPostState = {
  questionId: null,
  accepted: false,
  answers: [],
  body: '',
  comments: [],
  createDate: '',
  likes: 0,
  modDate: '',
  owner: '',
  tag: [],
  title: '',
  views: 0,
};

export const fetchPost = createAsyncThunk(
  'postSlice/fetchPost',
  async (url) => {
    const resposeData = await axios.get(url);
    try {
      return resposeData.data.question;
    } catch (error) {
      throw new Error('get요청 에러 발생');
    }
  }
);
export const addAnswer = createAsyncThunk(
  'postSlice/addAnswer',
  async (data) => {
    const responseData = await axios.post(data.url, {
      body: JSON.stringify(data.requestbody),
      headers: { 'Content-Type': 'application/json' },
    });
    try {
      return responseData.data.answer;
    } catch (error) {
      throw new Error('답변추가 에러');
    }
  }
);

const postSlice = createSlice({
  name: 'post',
  initialState: initialPostState,
  reducers: {
    // plusLike: (state) => {},
    // minusLike: (state) => {},
    // editPost:
    // deletePost:
    // addComment: (state, action) => {
    //   state.comments = state.comments.push(action.payload);
    // },
    // edit: (state, action) => {},
    // deleteComment: (state, action) => {},
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchPost.pending, (/*state, action*/) => {})
      .addCase(fetchPost.fulfilled, (state, action) => {
        return (state = action.payload);
      })
      .addCase(fetchPost.rejected, (state, action) => {
        console.log(action.error.message);
      })
      .addCase(addAnswer.fulfilled, (state, action) => {
        console.log('action', action.payload.body);
        // return state.answers.push(action.payload.body);
      });
  },
});

export default postSlice.reducer;
export const postActions = postSlice.actions;

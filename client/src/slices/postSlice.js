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

/** Post.js */
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

/** Answer.js */
export const addAnswer = createAsyncThunk(
  'postSlice/addAnswer',
  async (data) => {
    const responseData = await axios.post(
      data.url,
      { body: data.requestbody },
      {
        headers: { 'Content-Type': 'application/json' },
      }
    );
    try {
      return responseData.data.answer;
    } catch (error) {
      throw new Error('답변 추가 에러');
    }
  }
);

/** ViewContainer.js */
export const deleteSomething = createAsyncThunk(
  'postSlice/deleteSomething',
  async (data) => {
    const responseData = await axios.delete(data.url);
    try {
      const payload = {
        newLists: responseData.data.answer,
        type: data.target,
      };
      return payload;
    } catch (error) {
      throw new Error('삭제 오류 발생');
    }
  }
);

export const editAnswer = createAsyncThunk(
  'postSlice/editAnswer',
  async (data) => {
    const responseData = await axios.put(
      data.url,
      { body: data.requestbody },
      {
        headers: { 'Content-Type': 'application/json' },
      }
    );
    try {
      return responseData.data.answer;
    } catch (error) {
      throw new Error('답변 수정 에러');
    }
  }
);

/** CommentContainer.js */
export const addComment = createAsyncThunk(
  'postSlice/addComment',
  async (data) => {
    const responseData = await axios.post(
      data.url,
      { body: data.requestbody },
      {
        headers: { 'Content-Type': 'application/json' },
      }
    );
    try {
      const payload = {
        newLists: responseData.data.comment,
        targetId: data.id,
      };
      return payload;
    } catch (error) {
      throw new Error('답변 수정 에러');
    }
  }
);

const postSlice = createSlice({
  name: 'post',
  initialState: initialPostState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchPost.fulfilled, (state, action) => {
        return (state = action.payload);
      })
      .addCase(fetchPost.rejected, (state, action) => {
        console.log(action.error.message);
      })
      .addCase(addAnswer.fulfilled, (state, action) => {
        state.answers.push(action.payload);
      })
      .addCase(editAnswer.fulfilled, (state, action) => {
        const changedAnswerIdx = state.answers.findIndex(
          (answer) => answer.answerId === action.payload.answerId
        );
        state.answers[changedAnswerIdx] = action.payload;
      })
      .addCase(deleteSomething.fulfilled, (state, action) => {
        if (action.payload.type === 'question') {
          return initialPostState;
        } else {
          state.answers = action.payload.newLists;
        }
      })
      .addCase(addComment.fulfilled, (state, action) => {
        if (action.payload.newLists[0].questionCommentId) {
          state.comments = action.payload.newLists;
        } else {
          const answerIndex = state.answers.findIndex(
            (answers) => answers.answerId === action.payload.targetId
          );
          state.answers[answerIndex].comments = action.payload.newLists;
        }
      });
  },
});

export default postSlice.reducer;
export const postActions = postSlice.actions;

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
/** 아래 코드는 요청 URL에 자동으로 question이 붙게 됩니다. */
export const deleteSomething = createAsyncThunk(
  'postSlice/deleteSomething',
  async (data) => {
    console.log(data);
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
    // console.log(data.url);
    // console.log(data.requestbody);
    const responseData = await axios.put(
      data.url,
      { body: data.requestbody },
      {
        headers: { 'Content-Type': 'application/json' },
      }
    );
    try {
      return responseData.data;
    } catch (error) {
      throw new Error('답변 수정 에러');
    }
  }
);

/** CommentContainer.js */
/** 아래 코드는 요청 URL에 자동으로 question이 붙게 됩니다. */
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
      return responseData.data.comment;
      // console.log('코맨트 추가 후 성공', responseData);
      // 업데이트된 답글 return 예정
    } catch (error) {
      throw new Error('답변 수정 에러');
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
      .addCase(fetchPost.fulfilled, (state, action) => {
        return (state = action.payload);
      })
      .addCase(fetchPost.rejected, (state, action) => {
        console.log(action.error.message);
      })
      .addCase(addAnswer.fulfilled, (state, action) => {
        state.answers.push(action.payload);
      })
      // .addCase(editAnswer.fulfilled, (/* state, action */) => {
      //   // 수정 성공후 반환된 answer의 id와 일치하는 부분을 대체
      //   // url 문제로 보류했습니다.
      // })
      .addCase(deleteSomething.fulfilled, (state, action) => {
        if (action.payload.type === 'question') {
          return initialPostState;
        } else {
          state.answers = action.payload.newLists;
        }
      })
      .addCase(addComment.fulfilled, (state, action) => {
        action.payload[0].questionCommentId
          ? (state.comments = action.payload)
          : null;
        // console.log(action.payload);
        /* 답변의 댓글인 경우 추가할 예정입니다. */
      });
  },
});

export default postSlice.reducer;
export const postActions = postSlice.actions;

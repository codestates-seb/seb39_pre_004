import { configureStore } from '@reduxjs/toolkit';
import commentReducer from '../slices/commentSlice';
import postReducer from '../slices/postSlice';
import addReducer from '../slices/addSlice';
import inputAnswerReducer from '../slices/inputAnswerSlice';

export const store = configureStore({
  reducer: {
    comment: commentReducer,
    singlePost: postReducer,
    add: addReducer,
    inputAnswer: inputAnswerReducer,
    /* 슬라이스에 매칭되는 리듀서를 넣어주세요. */
  },
});

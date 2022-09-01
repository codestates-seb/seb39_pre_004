import { configureStore } from '@reduxjs/toolkit';
import commentReducer from '../slices/commentSlice';
import postReducer from '../slices/postSlice';

export const store = configureStore({
  reducer: {
    comment: commentReducer,
    singlePost: postReducer,
    /* 슬라이스에 매칭되는 리듀서를 넣어주세요. */
  },
});

import { configureStore } from '@reduxjs/toolkit';
import booleanTypeReducer from '../slices/booleanTypeSlice';
import commentReducer from '../slices/commentSlice';

export const store = configureStore({
  reducer: {
    booleanType: booleanTypeReducer,
    comment: commentReducer,
    /* 슬라이스에 매칭되는 리듀서를 넣어주세요. */
  },
});

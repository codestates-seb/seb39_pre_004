import { configureStore } from '@reduxjs/toolkit';
import booleanTypeReducer from '../slices/booleanTypeSlice';
export const store = configureStore({
  reducer: {
    booleanType: booleanTypeReducer,
    /* 슬라이스에 매칭되는 리듀서를 넣어주세요. */
  },
});

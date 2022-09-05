import { configureStore } from '@reduxjs/toolkit';
import postReducer from '../slices/postSlice';
import addReducer from '../slices/addSlice';
import userReducer from '../slices/userSlice';
import inputReducer from '../slices/inputSlice';

export const store = configureStore({
  reducer: {
    singlePost: postReducer,
    add: addReducer,
    user: userReducer,
    input: inputReducer,
    /* 슬라이스에 매칭되는 리듀서를 넣어주세요. */
  },
});

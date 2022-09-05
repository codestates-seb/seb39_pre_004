import {
  combineReducers,
  configureStore,
  getDefaultMiddleware,
} from '@reduxjs/toolkit';
import postReducer from '../slices/postSlice';
import addReducer from '../slices/addSlice';
import userReducer from '../slices/userSlice';
import inputReducer from '../slices/inputSlice';
import editReducer from '../slices/editSlice';

import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';

const reducers = combineReducers({
  user: userReducer,
});

const persistConfig = {
  key: 'root', // localStorage에 userReducer 저장
  storage,
};

const persistedReducer = persistReducer(persistConfig, reducers);

export const store = configureStore({
  reducer: {
    persistedReducer,
    singlePost: postReducer,
    add: addReducer,
    user: userReducer,
    input: inputReducer,
    edit: editReducer,
    /* 슬라이스에 매칭되는 리듀서를 넣어주세요. */
  },
  // A non-serializable value 에러 방지
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});

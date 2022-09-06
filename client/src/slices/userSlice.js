import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const initialUserState = {
  loading: false,
  userEmail: null,
  userToken: localStorage.getItem('userToken'), // jwt
  success: localStorage.getItem('userToken') ? true : false, // 현재 로그인 상태
  error: null,
};

// 로그인 액션 생성자
export const userLogin = createAsyncThunk(
  'userSlice/userLogin ',
  async (userData) => {
    try {
      const data = await axios.post('/api/login', userData);
      if (data.status === 200) {
        localStorage.clear();
        localStorage.setItem('userToken', data.headers.authorization);
      }
      return data;
    } catch (error) {
      if (error.response && error.response.data.message) {
        return error.response.data.message;
      } else {
        return error.message;
      }
    }
  }
);

// 회원가입 액션 생성자
export const userSignUp = createAsyncThunk(
  'userSlice/userSignUp ',
  async (signUpData) => {
    try {
      const data = await axios.post('/api/users/signup', signUpData);
      if (data.status === 200) {
        localStorage.clear();
      }
    } catch (error) {
      if (error.response && error.response.data.message) {
        return error.response.data.message;
      } else {
        return error.message;
      }
    }
  }
);

// 로그아웃 액션 생성자
export const userLogout = createAsyncThunk(
  'userSlice/userLogout ',
  async () => {
    try {
      await axios.post('/api/logout', {
        headers: { authorization: localStorage.getItem('userToken') },
      });
    } catch (error) {
      if (error.response && error.response.data.message) {
        return error.response.data.message;
      } else {
        return error.message;
      }
    }
  }
);

const userSlice = createSlice({
  name: 'user',
  initialState: initialUserState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(userLogin.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(userLogin.fulfilled, (state, action) => {
        state.loading = false;
        // 인가된 사용자라면 success 상태값 true로 변경
        if (action.payload !== 'Unauthorized') {
          state.success = true;
        }
        state.userEmail = action.meta.arg.email;
      })
      .addCase(userLogin.rejected, (state, { payload }) => {
        state.loading = false;
        state.error = payload;
      });
  },
});

export default userSlice.reducer;
export const userActions = userSlice.actions;

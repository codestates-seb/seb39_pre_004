import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const initialUserState = {
  loading: false,
  userEmail: null,
  userToken: localStorage.getItem('userToken'), // jwt
  success: false, // 현재 로그인 상태
  error: null,
};

// createAsyncThunk : 비동기 작업을 처리하는 액션 생성자
export const userLogin = createAsyncThunk(
  'userSlice/userLogin ',
  async (userData) => {
    try {
      const data = await axios.post('/login', userData);
      localStorage.clear();
      localStorage.setItem('userToken', data.headers.authorization);
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
        if (action.payload !== 'Unauthorized') {
          state.success = true;
        }
        state.userEmail = action.meta.arg.email;
        console.log(action);
      })
      .addCase(userLogin.rejected, (state, { payload }) => {
        state.loading = false;
        state.error = payload;
      });
  },
});

export default userSlice.reducer;
export const userActions = userSlice.actions;

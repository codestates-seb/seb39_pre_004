import { createSlice } from '@reduxjs/toolkit';

const initialInputSlice = {
  value: '',
  commentValue: '',
};
const inputSlice = createSlice({
  name: 'input',
  initialState: initialInputSlice,
  reducers: {
    answer: (state, action) => {
      state.value = action.payload;
    },
    comment: (state, action) => {
      state.commentValue = action.payload;
    },
  },
});
export default inputSlice.reducer;
export const inputAction = inputSlice.actions;

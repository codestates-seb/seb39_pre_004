import { createSlice } from '@reduxjs/toolkit';

const initialInputSlice = {
  value: '',
};
const inputAnswerSlice = createSlice({
  name: 'input',
  initialState: initialInputSlice,
  reducers: {
    fill: (state, action) => {
      state.value = action.payload;
    },
  },
});
export default inputAnswerSlice.reducer;
export const inputAction = inputAnswerSlice.actions;

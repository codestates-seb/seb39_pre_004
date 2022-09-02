import { createSlice } from '@reduxjs/toolkit';

const initialInputSlice = {
  value: '',
};
const inputSlice = createSlice({
  name: 'input',
  initialState: initialInputSlice,
  reducers: {
    fill: (state, action) => {
      state.value = action.payload;
    },
  },
});
export default inputSlice.reducer;
export const inputAction = inputSlice.actions;

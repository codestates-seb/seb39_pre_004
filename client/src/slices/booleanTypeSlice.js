import { createSlice } from '@reduxjs/toolkit';

const booleanTypeSlice = createSlice({
  name: 'booleanType',
  initialState: {
    value: false,
  },
  reducers: {
    /* 타입별로 리듀서 정의해주세요 */
    bookmark: (state) => {
      state.value = !state.value;
    },
  },
});
export default booleanTypeSlice.reducer;
export const { bookmark } = booleanTypeSlice.actions;

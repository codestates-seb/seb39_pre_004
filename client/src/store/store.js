import { configureStore, createSlice } from '@reduxjs/toolkit';

const booleanTypeSlice = createSlice({
  name: 'booleanType',
  initialState: {
    value: false,
  },
  reducers: {
    /* 타입별 리듀서 정의 */
    bookmark: (state) => {
      state.value = !state.value;
    },
  },
});
export const store = configureStore({
  reducer: {
    booleanType: booleanTypeSlice.reducer,
    /* 슬라이스에 매칭되는 리듀서를 넣어주세요. */
  },
});

import { createSlice } from '@reduxjs/toolkit';

const initialCommentState = {
  checked: false,
  commentList: [],
};

const commentSlice = createSlice({
  name: 'comment',
  initialState: initialCommentState,
  reducers: {
    setChecked: (state) => {
      state.checked = !state.checked;
    },
    addComment: (state, action) => {
      state.commentList = state.commentList.push(action.payload);
    },
    // edit: (state, action) => {},
    // deleteComment: (state, action) => {},
  },
});
export default commentSlice.reducer;
export const commentActions = commentSlice.actions;

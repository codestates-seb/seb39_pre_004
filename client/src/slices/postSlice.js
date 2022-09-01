import { createSlice } from '@reduxjs/toolkit';

const initialPostState = {
  questionID: 1,
  title: 'IN memory Graph DB with specific requirements',
  body: 'We are looking for some in memory graph data kind of solution . Where we can store the graph data like the below',
  owner: {
    bio: '와드입니다',
    email: 'ward@ward.com',
    image: null,
    link: null,
    name: 'ward',
    userId: 1,
  },
  comments: [],
  answers: [
    {
      accepted: false,
      answerId: 1,
      body: '이건아닌데요',
      comments: [],
      createDate: '2022-09-01 14:48:13',
      likes: 2,
      modDate: '2022-09-01 14:48:13',
      owner: {
        userId: 2,
        name: '답글ward',
        email: 'ward@ward.net',
        image: null,
        bio: '답글쓰는사람입니다.',
        link: null,
      },
    },
  ],
  accepted: false,
  createDate: '2022-08-30',
  likes: 0,
  modDate: '2022-08-30',
  tag: ['javascript'],
  views: 0,
  // questionID: null,
  // accepted: false,
  // answers: [],
  // body: null,
  // comments: [],
  // createDate: null,
  // likes: 0,
  // modDate: null,
  // owner: null,
  // tag: [],
  // title: '초기값',
  // views: 0,
};

const postSlice = createSlice({
  name: 'post',
  initialState: initialPostState,
  reducers: {
    // plusLike: (state) => {},
    // minusLike: (state) => {},
    // editPost:
    // deletePost:
    // addComment: (state, action) => {
    //   state.comments = state.comments.push(action.payload);
    // },
    // edit: (state, action) => {},
    // deleteComment: (state, action) => {},
  },
});
export default postSlice.reducer;
export const postActions = postSlice.actions;

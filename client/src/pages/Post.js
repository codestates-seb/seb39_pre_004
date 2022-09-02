import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { fetchPost } from '../slices/postSlice';

import Question from '../components/PostLayout/Question';
import Answer from '../components/PostLayout/Answer';

const Post = ({ key }) => {
  const dispatch = useDispatch();

  key = 1;
  const url = `/questions/${key}`;

  useEffect(() => {
    dispatch(fetchPost(url));
  }, [dispatch]);

  return (
    <>
      <Question />
      <Answer />
    </>
  );
};
export default Post;

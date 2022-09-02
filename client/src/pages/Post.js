import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { fetchPost } from '../slices/postSlice';

import Question from '../components/PostLayout/Question';
import Answer from '../components/PostLayout/Answer';

const Post = () => {
  const { id } = useParams();
  const url = `/questions/${id}`;

  const dispatch = useDispatch();

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

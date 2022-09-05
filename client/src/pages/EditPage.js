import { useSelector, useDispatch } from 'react-redux';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { editFetch } from '../slices/editSlice';
import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import BlueButton from '../components/Bluebutton';
import Textarea from '../components/CommonLayout/Textarea';

const Edit = () => {
  const store = useSelector((state) => state.singlePost);
  const { questionId, title, body } = store;

  const [editTitle, setEditTitle] = useState(title);
  const [editBody, setEditBody] = useState(body);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    const editRequest = {
      url: `/questions/${questionId}/edit`,
      title: editTitle,
      body: editBody,
    };
    dispatch(editFetch(editRequest));
    navigate(`/questions/${questionId}`);
  };

  return (
    <>
      <Subtitle>Title</Subtitle>
      <Input
        placeholder="e.g is threr an R function for finding the index if an element in a vector"
        value={editTitle}
        onChange={(e) => setEditTitle(e.target.value)}
      />

      <Subtitle>Body</Subtitle>
      <Textarea
        value={editBody}
        onChange={(e) => setEditBody(e.target.value)}
      />
      <Subtitle>Tags</Subtitle>
      <Input placeholder="e.g &#40;c# java wpf&#41;" />
      <BlueButton onClick={handleSubmit}>Save edits</BlueButton>
    </>
  );
};

export default Edit;

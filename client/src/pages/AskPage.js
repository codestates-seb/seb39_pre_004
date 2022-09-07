import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { asyncAddFetch } from '../slices/addSlice';
import SubHeader from '../components/SubHeader';
import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import Textarea from '../components/CommonLayout/Textarea';
import BlueButton from '../components/Bluebutton';

const Ask = () => {
  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const addRequest = {
    title,
    body,
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(asyncAddFetch(addRequest)).then((res) => {
      navigate(`/questions/${res.payload}`);
    });
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <SubHeader>Ask a public question</SubHeader>
        <Subtitle>
          Title
          <p>
            Be specific and imagine you’re asking a question to another person
          </p>
        </Subtitle>
        <Input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="e.g is threr an R function for finding the index if an element in a vector"
        />
        <Subtitle>
          Body
          <p>
            Include all the information someone would need to answer your
            question
          </p>
        </Subtitle>
        <Textarea value={body} onChange={(e) => setBody(e.target.value)} />
        <Subtitle>Tags</Subtitle>
        <Input
          type="text"
          placeholder="e.g. &#40;ruby-on-rails.net sql-server&#40;"
        />
        <BlueButton type="submit">Post question</BlueButton>
      </form>
    </>
  );
};

export default Ask;

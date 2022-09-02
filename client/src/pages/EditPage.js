import { useState } from 'react';
import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import BlueButton from '../components/Bluebutton';
import styled from 'styled-components';

const Textarea = styled.textarea`
  width: 100%;
  height: 250px;
  border: 1px solid var(--gray);
  resize: none;
  padding: 10px;
  margin-bottom: 20px;
`;

const Edit = () => {
  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');

  const [isEdit, setIsEdit] = useState(false);

  return (
    <>
      <Subtitle>Title</Subtitle>
      <Input
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="e.g is threr an R function for finding the index if an element in a vector"
      />
      <Subtitle>Body</Subtitle>
      <Textarea value={body} onChange={(e) => setBody(e.target.value)} />
      <Subtitle>Tags</Subtitle>
      <Input
        type="text"
        placeholder="e.g. &#40;ruby-on-rails.net sql-server)"
      />
      <BlueButton value={isEdit} onClick={() => setIsEdit(true)}>
        Save edits
      </BlueButton>
    </>
  );
};

export default Edit;

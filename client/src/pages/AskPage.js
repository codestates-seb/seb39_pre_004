import SubHeader from '../components/SubHeader';
import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import TextEditor from '../components/TextEditor';
import BlueButton from '../components/Bluebutton';
import { useState } from 'react';

const Ask = () => {
  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');

  const handleSubmit = (e) => {
    e.preventDefalut();
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
          type="text"
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
        <TextEditor value={body} onChange={(e) => setBody(e.target.value)} />
        <Subtitle>Tags</Subtitle>
        <Input
          type="text"
          placeholder="e.g. &#40;ruby-on-rails.net sql-server&#40;"
        />
      </form>
      <BlueButton>Post question</BlueButton>
    </>
  );
};

export default Ask;

import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import TextEditor from '../components/TextEditor';
import BlueButton from '../components/Bluebutton';

const Edit = () => {
  return (
    <>
      <Subtitle>Title</Subtitle>
      <Input
        type="text"
        placeholder="e.g is threr an R function for finding the index if an element in a vector"
      />
      <Subtitle>Body</Subtitle>
      <TextEditor />
      <Subtitle>Tags</Subtitle>
      <Input
        type="text"
        placeholder="e.g. &#40;ruby-on-rails.net sql-server)"
      />
      <BlueButton>Save edits</BlueButton>
    </>
  );
};

export default Edit;

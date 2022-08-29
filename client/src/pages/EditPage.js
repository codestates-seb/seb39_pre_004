import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import Textarea from '../components/TextEditor';
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
      <Textarea placeholder="More info about your question. You can use markdown here" />
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

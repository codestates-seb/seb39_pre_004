import SubHeader from '../components/SubHeader';
import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import Textarea from '../components/TextEditor';
import BlueButton from '../components/Bluebutton';

function Ask() {
  return (
    <>
      <SubHeader>Ask a public question</SubHeader>
      <Subtitle>
        Title
        <p>
          Be specific and imagine you’re asking a question to another person
        </p>
      </Subtitle>
      <Input
        type="text"
        placeholder="e.g is threr an R function for finding the index if an element in a vector"
      />
      <Subtitle>
        Body
        <p>
          Include all the information someone would need to answer your question
        </p>
      </Subtitle>
      <Textarea placeholder="More info about your question. You can use markdown here" />
      <Subtitle>Tags</Subtitle>
      <Input
        type="text"
        placeholder="e.g. &#40;ruby-on-rails.net sql-server&#40;"
      />
      <BlueButton>Post question</BlueButton>
    </>
  );
}

export default Ask;

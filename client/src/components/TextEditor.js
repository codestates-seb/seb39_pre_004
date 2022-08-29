import styled from 'styled-components';

const Textarea = styled.textarea`
  background: none;
  border: 1px solid #777;
  border-radius: 3px;
  display: block;
  width: 100%;
  box-sizing: border-box;
  padding: 10px;
  min-height: 200px;
  margin-bottom: 20px;
  font-family: inherit;
`;

function TextEditor() {
  return (
    <>
      <Textarea />
    </>
  );
}

export default TextEditor;

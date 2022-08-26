import React from 'react'; // eslint-disable-line no-unused-vars
import styled from 'styled-components';

const TitleBlock = styled.div`
  margin-bottom: 10px;
  input {
    width: 100%;
    height: 30px;
  }
`;

const ContentBlock = styled.div`
  textarea {
    width: 100%;
    height: 300px;
  }
`;

const TagBlock = styled.div`
  input {
    width: 100%;
    height: 30px;
  }
`;

const Button = styled.div`
  display: inline-block;
  padding: 0.8em;
  margin-top: 1em;
  background-color: #0d95fc;
  height: 25px;
  line-height: 25px;
  color: #fff;
  font-size: 0.7rem;
  border-radius: 5px;
`;

function AskEditor() {
  return (
    <>
      <TitleBlock>
        <h4>Title</h4>
        <input type="title" />
      </TitleBlock>

      <ContentBlock>
        <textarea></textarea>
      </ContentBlock>

      <TagBlock>
        <h4>Tags</h4>
        <input type="tag" />
      </TagBlock>

      <Button>Review your question</Button>
    </>
  );
}

export default AskEditor;

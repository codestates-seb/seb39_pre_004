import React from 'react'; // eslint-disable-line no-unused-vars
import AskEditor from '../components/AskEditor';
import styled from 'styled-components';

const AskContainer = styled.div`
  width: 60%;
  margin: 0 auto;
`;

function Ask() {
  return (
    <AskContainer>
      <AskEditor />
    </AskContainer>
  );
}

export default Ask;

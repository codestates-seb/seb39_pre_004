import styled from 'styled-components';

const Input = styled.input`
  background: none;
  border: 1px solid var(--gray);
  border-radius: 3px;
  display: block;
  width: 100%;
  box-sizing: border-box;
  padding: 10px;
  margin-bottom: 20px;
  ::placeholder {
    color: #bbb;
  }
`;

export default Input;

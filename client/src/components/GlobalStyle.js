import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
 @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300,400;700&display=swap');
 * {
    margin: 0;
    padding: 0;  
  }
  
  body {
    font-family: 'Roboto', sans-serif;
    box-sizing: border-box;
  }

  h1 {
    font-size: 1.5rem;
  }

  p {
    font-size: 1rem;
    line-height: 1.5;
  }
  
  button{
    border: none;
    outline: none;
    cursor: pointer;
  }
`;

export default GlobalStyle;

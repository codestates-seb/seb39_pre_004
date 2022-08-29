import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300,400;700&display=swap');
 * {
    list-style: none;
    text-decoration: none;
    margin: 0;
    padding: 0;  
  }

  body {
    background: #ffffff;
    padding-top: 70px;
    font-family: 'Roboto', sans-serif;
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

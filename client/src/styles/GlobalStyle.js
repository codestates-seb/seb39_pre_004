import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
 * {
    list-style: none;
    text-decoration: none;
    margin: 0;
    padding: 0;  
    box-sizing: border-box;
    color: var(--basic);
  }

  :root {
  --basic: #3b4045;
  --orange: #F48225;
  --blue: #0a95ff;
  --deep-gray:#6a737c;
  --dark-gray: #838C95;
  --gray: #dddddd;
  --light-gray: #f8f9f9;
  --black: #232629;
  --white: #ffffff;
}

  body {
    background: #ffffff;
    padding-top: 50px;
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

  main{
    padding: 30px;
    margin-left: 164px;
  }
`;

export default GlobalStyle;

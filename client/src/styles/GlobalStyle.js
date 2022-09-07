import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
 * {
    list-style: none;
    text-decoration: none;
    margin: 0;
    padding: 0;  
    box-sizing: border-box;
  }

  :root {
  --basic: #3b4045;
  --orange: #F48225;
  --red: #DE4F54;
  --blue: #0a95ff;
  --deep-blue: #0074cc;
  --blue-bg: #d9eaf7;
  --deep-gray:#6a737c;
  --dark-gray: #838C95;
  --gray-text: #b5babf;
  --gray-bar:#e3e6e8;
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

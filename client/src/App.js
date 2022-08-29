import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import CommonLayout from './components/CommonLayout';
import AskPage from './pages/AskPage';
import EditPage from './pages/EditPage';
import Post from './pages/Post';

const GlobalStyle = createGlobalStyle`
  body {
    background: #ffffff;
    overflow-y: scroll;
    padding-top: 70px;
  }

  * {
    list-style: none;
    text-decoration: none;
    margin: 0;
    padding: 0;
  }
`;

const App = () => {
  return (
    <BrowserRouter>
      <GlobalStyle />
      <Routes>
        <Route path="/" element={<CommonLayout />}>
          <Route path="/login" element={<div>login</div>}></Route>
          <Route path="/ask" element={<AskPage />}></Route>
          <Route path="/edit" element={<EditPage />}></Route>
          <Route path="/questions" element={<Post />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;

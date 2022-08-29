import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CommonLayout from './components/CommonLayout';
import GlobalStyle from './styles/GlobalStyle';
import AskPage from './pages/AskPage';
import Mypage from './pages/Mypage';
import EditPage from './pages/EditPage';
import Post from './pages/Post';

const App = () => {
  return (
    <BrowserRouter>
      <GlobalStyle />
      <Routes>
        <Route path="/" element={<CommonLayout />}>
          <Route path="/login" element={<div>login</div>}></Route>
          <Route path="/ask" element={<AskPage />}></Route>
          <Route path="/users" element={<Mypage />}></Route>
          <Route path="/edit" element={<EditPage />}></Route>
          <Route path="/questions" element={<Post />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;

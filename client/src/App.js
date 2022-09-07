import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CommonLayout from './components/CommonLayout/CommonLayout';
import GlobalStyle from './styles/GlobalStyle';
import AskPage from './pages/AskPage';
import Mypage from './pages/MyPage';
import EditPage from './pages/EditPage';
import Post from './pages/Post';
import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import JoinPage from './pages/JoinPage';
import { useSelector } from 'react-redux';

const App = () => {
  useSelector((state) => state.user);

  return (
    <BrowserRouter>
      <GlobalStyle />
      <Routes>
        <Route path="/" element={<CommonLayout />}>
          <Route path="/" element={<MainPage />}></Route>
          <Route path="/login" element={<LoginPage />}></Route>
          <Route path="/signup" element={<JoinPage />}></Route>
          <Route path="/ask" element={<AskPage />}></Route>
          <Route path="/users" element={<Mypage />}></Route>
          <Route path="/questions/:id/edit" element={<EditPage />}></Route>
          <Route path="/questions/:id" element={<Post />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CommonLayout from './components/CommonLayout';
import Post from './pages/Post';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<CommonLayout />}>
          <Route path="/login" element={<div>login</div>}></Route>
          <Route path="/questions" element={<Post />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;

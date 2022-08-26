import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CommonLayout from './components/CommonLayout';
import AskPage from './pages/AskPage';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<CommonLayout />}>
          <Route path="/login" element={<div>login</div>}></Route>
          <Route path="/ask" element={<AskPage />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;

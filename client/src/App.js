import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CommonLayout from './components/CommonLayout';
import Ask from './pages/Ask';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<CommonLayout />}>
          <Route path="/login" element={<div>login</div>}></Route>
          <Route path="/ask" element={<Ask />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;

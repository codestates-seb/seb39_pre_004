import { Outlet } from 'react-router-dom';

const CommonLayout = () => {
  return (
    <div>
      <header>header</header>
      <main>
        <Outlet />
      </main>
      <footer>footer</footer>
    </div>
  );
};

export default CommonLayout;

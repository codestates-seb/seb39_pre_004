import { Outlet } from 'react-router-dom';
import styled from 'styled-components';
import Sidebar from './Sidebar';

const ContainerBlock = styled.div`
  width: 100%;
`;

const MainBlock = styled.div`
  height: 100vh;
  height: -webkit-fill-available;
  height: fill-available;
`;

const Main = () => {
  return (
    <ContainerBlock>
      <Sidebar />
      <main>
        <MainBlock>
          <Outlet />
        </MainBlock>
      </main>
    </ContainerBlock>
  );
};

export default Main;

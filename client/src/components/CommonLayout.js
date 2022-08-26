import { Outlet } from 'react-router-dom';
import styled from 'styled-components';

const HeaderBlock = styled.div`
  padding: 10px;
  border: 1px solid;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f8f9f9;
`;

const FooterBlock = styled.div`
  padding: 10px;
  border: 1px solid;
  display: flex;
  background: #232629;
  color: #ffffff;
`;

const NavBlock = styled.div`
  padding: 10px;
  background-color: aliceblue;
  border: 1px solid;
`;

const CommonLayout = () => {
  return (
    <div>
      <header>
        <HeaderBlock>
          <a href="*">
            <span>Stack Overflow</span>
          </a>
          <input type="search" />
          <button>Log in</button>
          <button>Sign up</button>
        </HeaderBlock>
      </header>

      <div>
        <nav>
          <NavBlock>nav</NavBlock>
        </nav>
        <main>
          <Outlet />
        </main>
      </div>

      <footer>
        <FooterBlock>
          <div>
            <div>footer logo</div>
            <div>footer nav</div>
            <div>footer copyright</div>
          </div>
        </FooterBlock>
      </footer>
    </div>
  );
};

export default CommonLayout;

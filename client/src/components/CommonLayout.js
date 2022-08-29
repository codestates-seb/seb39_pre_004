import { Outlet } from 'react-router-dom';
import styled from 'styled-components';
import BlueButton from './Bluebutton';

const HeaderBlock = styled.div`
  padding: 10px;
  border: 1px solid;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f8f9f9;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
`;

const FooterBlock = styled.div`
  padding: 10px;
  display: flex;
  flex-direction: row;
  background: #232629;
  color: #ffffff;
  position: absolute;
  width: 100vw;
  div {
    width: 100%;
  }
  ul {
    padding: 10px;
    display: block;
  }
  .footer-col {
    display: flex;
  }
`;

const SideBarBlock = styled.div`
  padding: 10px;
  background-color: #ffffff;
  border: 1px solid;
  width: 164px;
  max-height: 100%;
  height: 100vh;
  position: fixed;
  top: 71px;
`;

const ContainerBlock = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: baseline;
`;

const MainBlock = styled.div`
  display: block;
  margin-left: 230px;
  padding: 20px 0;
  &:first-child {
    width: 100vw;
  }
`;

const ButtonBlock = styled.div`
  button {
    margin: 10px;
  }
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
          <ButtonBlock>
            <BlueButton>profile</BlueButton>
            <BlueButton>Log in</BlueButton>
            <BlueButton>Sign up</BlueButton>
          </ButtonBlock>
        </HeaderBlock>
      </header>

      <ContainerBlock>
        <nav>
          <SideBarBlock>
            <ul>
              <li>
                <a href="*">Home</a>
              </li>
              <li>
                <a href="*">Tags</a>
              </li>
            </ul>
          </SideBarBlock>
        </nav>
        <main>
          <MainBlock>
            <Outlet />
          </MainBlock>
        </main>
      </ContainerBlock>

      <footer>
        <FooterBlock>
          <div>footer logo</div>
          <div className="footer-col">
            <div>
              <ul>
                <h5>STACK OVERFLOW</h5>
                <li>Ouestions</li>
                <li>Help</li>
              </ul>
            </div>
            <div>
              <ul>
                <h5>PRODUCTS</h5>
                <li>Teams</li>
                <li>Advertising</li>
                <li>Collectives</li>
                <li>Talent</li>
              </ul>
            </div>
            <div>
              <ul>
                <h5>COMPANY</h5>
                <li>About</li>
                <li>Press</li>
                <li>Work Here</li>
                <li>Privacy Policy</li>
              </ul>
            </div>
          </div>
          <div>
            Site design / logo © 2022 Stack Exchange Inc; user contributions
            licensed under CC BY-SA. rev 2022.8.26.42925
          </div>
        </FooterBlock>
      </footer>
    </div>
  );
};

export default CommonLayout;

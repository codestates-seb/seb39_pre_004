import styled from 'styled-components';
import Subtitle from '../components/Subtitle';
import BlueButton from '../components/Bluebutton';
import LoginSocial from '../components/SocialButton/LoginSocial';

const Container = styled.div`
  width: 100%;
  max-width: 280px;
  margin: 0 auto;
  padding: 50px 0;
`;

const LoginBlock = styled.div`
  position: relative;
  background: white;
  border-radius: 10px;
  box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.2);
  margin: 0 auto;
  margin-top: 30px;
  margin-bottom: 32px;
  display: flex;
  flex-direction: column;
  position: relative;
  border: 1px solid var(--light--gray);
  padding: 20px;
`;

const LoginInput = styled.input`
  background: none;
  border: 1px solid var(--gray);
  border-radius: 3px;
  display: block;
  width: 100%;
  box-sizing: border-box;
  padding: 7px;
  margin-bottom: 20px;
  &:focus {
    outline: none;
    border: 1px solid var(--blue);
    box-shadow: 0px 0px 2px var(--blue);
  }
`;

const LoginPage = () => {
  return (
    <>
      <Container>
        <LoginSocial />
        <LoginBlock>
          <div>
            <Subtitle>Email</Subtitle>
            <LoginInput />
          </div>
          <div>
            <Subtitle>Password </Subtitle>
            <LoginInput />
          </div>
          <BlueButton>Log in</BlueButton>
        </LoginBlock>
      </Container>
    </>
  );
};

export default LoginPage;

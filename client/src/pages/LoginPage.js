import styled from 'styled-components';
import Subtitle from '../components/Subtitle';
import BlueButton from '../components/Bluebutton';
import LoginSocial from '../components/SocialButton/LoginSocial';
import Input from '../components/Input';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { userLogin } from '../slices/userSlice';
import { useDispatch, useSelector } from 'react-redux';

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

  span {
    font-size: 15px;
    color: var(--red);
    display: inline-block;
    margin-bottom: 10px;
  }
`;

const LoginPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { success } = useSelector((state) => state.user);

  const [loginEmail, setLoginEmail] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const data = { email: loginEmail, password: loginPassword };

  useEffect(() => {
    if (success) {
      navigate('/');
    }
  }, [success]);

  const onLoginHandler = (e) => {
    e.preventDefault();
    dispatch(userLogin(data));
  };

  return (
    <>
      <Container>
        <LoginSocial />
        <form onSubmit={(e) => onLoginHandler(e)}>
          <LoginBlock>
            <div>
              <Subtitle>Email</Subtitle>
              <Input
                type="email"
                onChange={(e) => setLoginEmail(e.target.value)}
                required
              />
            </div>
            <div>
              <Subtitle>Password </Subtitle>
              <Input
                type="password"
                onChange={(e) => setLoginPassword(e.target.value)}
                required
              />
              {/* {!isLogin && <span>Email 또는 Password를 확인하세요.</span>} */}
            </div>
            <BlueButton type="submit">Log in</BlueButton>
          </LoginBlock>
        </form>
      </Container>
    </>
  );
};

export default LoginPage;

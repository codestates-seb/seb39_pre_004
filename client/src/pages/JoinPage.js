import styled from 'styled-components';
import Subtitle from '../components/Subtitle';
import BlueButton from '../components/Bluebutton';
import JoinSocial from '../components/SocialButton/JoinSocial';
import Input from '../components/Input';
import { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { userSignUp } from '../slices/userSlice';

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
    color: var(--red);
  }
`;

const JoinPage = () => {
  const name = useRef();
  const email = useRef();
  const password = useRef();
  const dispatch = useDispatch();
  const [msg, setMsg] = useState();

  const navigate = useNavigate();

  const onSignUpHandler = (e) => {
    e.preventDefault();
    const data = {
      name: name.current.value,
      email: email.current.value,
      password: password.current.value,
    };

    dispatch(userSignUp(data)).then((res) => {
      setMsg(res.payload);
      if (!msg) {
        alert('회원가입이 완료되었습니다.');
        navigate('/login');
      }
    });
  };

  return (
    <>
      <Container>
        <JoinSocial />
        <form onSubmit={(e) => onSignUpHandler(e)}>
          <LoginBlock>
            <div>
              <Subtitle>Display name</Subtitle>
              <Input required ref={name} />
            </div>
            <div>
              <Subtitle>Email </Subtitle>
              <Input type="email" required ref={email} />
            </div>
            <div>
              <Subtitle>Password </Subtitle>
              <Input type="password" required ref={password} />
              <span>{msg}</span>
            </div>
            <BlueButton>Sign up</BlueButton>
          </LoginBlock>
        </form>
      </Container>
    </>
  );
};

export default JoinPage;

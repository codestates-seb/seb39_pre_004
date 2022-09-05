import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Logo from '../../assets/images/logo.svg';
import BlueButton from '../Bluebutton';
import { useSelector } from 'react-redux';
//import { userLogout } from '../../slices/userSlice';

const SearchBar = styled.div`
  margin: 0px 30px;
  width: 300px;
  height: 32px;
  border-radius: 3px;
  background-color: var(--white);
  border: solid 1px rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;

  input {
    width: 260px;
    border: none;
    outline: none;
  }

  svg {
    margin: 10px;
  }
`;

const HeaderBlock = styled.div`
  padding: 10px;
  height: 50px;
  border-top: 3px solid var(--orange);
  display: flex;
  justify-content: space-around;
  align-items: center;
  background: var(--light-gray);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  box-shadow: 0px 1px 2px var(--gray);
  z-index: 1;

  @media screen and (max-width: 768px) {
    ${SearchBar} {
      display: none;
    }
  }
`;

const ButtonBlock = styled.div`
  button {
    margin: 10px;
  }
`;

const Header = () => {
  const { success } = useSelector((state) => state.user);
  //const dispatch = useDispatch();
  //const navigate = useNavigate();

  // const onLogOutHandler = (e) => {
  //   e.preventDefault();
  //   dispatch(userLogout()).then((res) => {
  //     console.log(res);
  //     if (res.data.status === 200) {
  //       alert('로그아웃 되었습니다.');
  //     }
  //   });
  // };

  return (
    <header>
      <HeaderBlock>
        <Link to="/">
          <img src={Logo} width="150" height="40" alt="" />
        </Link>
        <SearchBar>
          <svg aria-hidden="true" width="18" height="18" viewBox="0 0 18 18">
            <path
              d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z"
              fill="#838C95"
            ></path>
          </svg>
          <input type="search" placeholder="Search..." />
        </SearchBar>
        <ButtonBlock>
          {success ? (
            <>
              <Link to="/users">
                <BlueButton>profile</BlueButton>
              </Link>
              <BlueButton>Log Out</BlueButton>
              {/* <BlueButton onClick={onLogOutHandler}>Log Out</BlueButton> */}
            </>
          ) : (
            <>
              <Link to="/login">
                <BlueButton>Log in</BlueButton>
              </Link>
              <Link to="/signup">
                <BlueButton>Sign up</BlueButton>
              </Link>
            </>
          )}
        </ButtonBlock>
      </HeaderBlock>
    </header>
  );
};

export default Header;

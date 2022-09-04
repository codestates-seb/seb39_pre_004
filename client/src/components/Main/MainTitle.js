import BlueButton from '../Bluebutton';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';

const TitleContainer = styled.div`
  display: flex;
`;

const TitleBlock = styled.h1`
  font-weight: normal;
  flex: 1;
  text-overflow: ellipsis;
  overflow: hidden;
  word-break: break-word; // 두 줄 이상
  display: -webkit-box;
  -webkit-line-clamp: 1; // 원하는 라인수
  -webkit-box-orient: vertical;
`;

const MainTitle = () => {
  const { success } = useSelector((state) => state.user);

  return (
    <TitleContainer>
      <TitleBlock>All Questions</TitleBlock>
      {success ? (
        <Link to="/ask">
          <BlueButton>Ask Question</BlueButton>
        </Link>
      ) : (
        <Link to="/login">
          <BlueButton>Ask Question</BlueButton>
        </Link>
      )}
    </TitleContainer>
  );
};

export default MainTitle;

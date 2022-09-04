import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Bluebutton from '../Bluebutton';
import ViewContainer from './ViewContainer';

const Header = styled.header`
  width: 100%;
  margin-bottom: 0.7rem;
  display: flex;
  justify-content: space-between;

  @media (max-width: 640px) {
    flex-direction: column-reverse;
  }
`;

const BlueButton = styled(Bluebutton)`
  padding: 10px 14px;
  @media screen and (max-width: 1040px) {
    margin-bottom: 0.5rem;
    /* 오른쪽으로 붙이기 */
    /* align-self: flex-end !important; */
  }
`;

const H1 = styled.h1`
  font-size: 1.7rem;
  font-weight: 500;
  flex: 1 auto !important;
  @media (max-width: 640px) {
    font-size: 1.5rem;
  }
`;

const InfoContainer = styled.div`
  display: flex;
  gap: 1rem;
  padding-bottom: 0.7rem;
  border-bottom: 1px var(--gray) solid;
`;

const InfoTitle = styled.div`
  color: var(--deep-gray);
  font-size: 0.8rem;
  font-weight: 500;
`;

const InfoValue = styled(InfoTitle)`
  color: var(--basic);
`;

const InfoDiv = styled.div`
  display: flex;
  justify-content: baseline;
  gap: 0.4rem;
`;

const Info = ({ keyword, value }) => {
  return (
    <InfoDiv>
      <InfoTitle>{keyword}</InfoTitle>
      <InfoValue>{value}</InfoValue>
    </InfoDiv>
  );
};

const Question = () => {
  const store = useSelector((state) => state.singlePost);
  const { questionId, title, createDate, modDate, views } = store;
  return (
    <>
      <Header>
        <H1>{title}</H1>
        <Link to="/ask">
          <BlueButton>Ask Question</BlueButton>
          {/* <Bluebutton>Ask Question</Bluebutton> */}
        </Link>
      </Header>
      <InfoContainer>
        <Info keyword="Asked" value={createDate.slice(0, 10)} />
        <Info keyword="Modified" value={modDate.slice(0, 10)} />
        <Info keyword="Views" value={`${views} times`} />
      </InfoContainer>
      <ViewContainer key={questionId} data={store} />
    </>
  );
};
export default Question;

import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Bluebutton from '../Bluebutton';
import ViewContainer from './ViewContainer';

const Header = styled.header`
  display: flex;
  flex-direction: column;
`;
const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
`;
const H1 = styled.h1`
  flex: 1;
`;

const InfoPost = ({ keyword, value }) => {
  return (
    <>
      <span>{keyword}</span>
      <span>{value}</span>
    </>
  );
};

const Question = () => {
  const store = useSelector((state) => state.singlePost);
  const { questionID, title, createDate, modiDate, views } = store;
  return (
    <>
      <Header>
        <TitleContainer>
          <H1>{title}</H1>
          <Link to="/ask">
            <Bluebutton>Ask Question</Bluebutton>
          </Link>
        </TitleContainer>
        <div>
          <InfoPost keyword="Asked" value={createDate} />
          <InfoPost keyword="Modified" value={modiDate} />
          <InfoPost keyword="Views" value={`${views} times`} />
        </div>
      </Header>
      <ViewContainer key={questionID} data={store} isAnswer={false} />
    </>
  );
};
export default Question;

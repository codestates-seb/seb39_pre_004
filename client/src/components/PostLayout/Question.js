import styled from 'styled-components';
import { Link } from 'react-router-dom';
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
  return (
    <>
      <Header>
        <TitleContainer>
          <H1>{'question title'}</H1>
          <Link to="/ask">
            <Bluebutton>Ask Question</Bluebutton>
          </Link>
        </TitleContainer>
        <div>
          <InfoPost keyword={'Asked'} value={'today'} />
          <InfoPost keyword={'Modified'} value={'todat'} />
          <InfoPost keyword={'Views'} value={`${5} times`} />
        </div>
      </Header>
      <ViewContainer isAnswer={false} />
    </>
  );
};
export default Question;

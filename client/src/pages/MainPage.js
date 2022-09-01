import MainTitle from '../components/Main/MainTitle';
import QuestionList from '../components/Main/QuestionList';
import styled from 'styled-components';

const MainContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const MainPage = ({ questions }) => {
  return (
    <MainContainer>
      <MainTitle />
      {questions && <QuestionList questions={questions} />}
    </MainContainer>
  );
};

export default MainPage;

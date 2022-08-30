import MainTitle from '../components/Main/MainTitle';
import QuestionList from '../components/Main/QuestionList';
// import SortButton from '../components/Main/SortButton';
import styled from 'styled-components';

const MainContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const MainPage = () => {
  return (
    <MainContainer>
      <MainTitle />
      {/* <SortButton /> */}
      <QuestionList />
    </MainContainer>
  );
};

export default MainPage;

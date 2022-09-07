import MainTitle from '../components/Main/MainTitle';
import QuestionList from '../components/Main/QuestionList';
import styled from 'styled-components';
import { useEffect, useState } from 'react';
import axios from 'axios';

const MainContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const MainPage = () => {
  const [mainData, setMainData] = useState(null);

  const getMainData = async () => {
    const { data } = await axios.get('/main');
    setMainData(data.question);
  };

  useEffect(() => {
    getMainData();
  }, []);

  return (
    <MainContainer>
      <MainTitle />
      {mainData && <QuestionList mainData={mainData} />}
    </MainContainer>
  );
};

export default MainPage;

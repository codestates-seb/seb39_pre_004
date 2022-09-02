import { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { addAnswer } from '../../slices/postSlice';
import styled from 'styled-components';
import BlueButton from '../Bluebutton';
import ViewContainer from './ViewContainer';
import TextEditer from '../TextEditor';

const SortMenu = styled.div`
  display: flex;
  justify-content: space-between;
`;
const SeletContainer = styled.div`
  display: flex;

  & > :first-child {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
  }
  .sortTypeContainer {
  }
`;

const Answer = () => {
  const dispatch = useDispatch();
  const [answerBody, setAnswerBody] = useState('텍스트 에디터 value값');
  const store = useSelector((state) => state.singlePost);
  const { answers, questionId } = store;

  const handleSubmit = () => {
    setAnswerBody('린트에러떄문에 넣은 것');
    const dataForThunk = {
      url: `/answers/${questionId}/add` /* url변경 필요 */,
      requestbody: answerBody,
    };

    dispatch(addAnswer(dataForThunk));
  };
  return (
    <>
      <section className="viewAnswer">
        <SortMenu>
          <div>{answers.length} Answers</div>
          <SeletContainer>
            <div>
              <label htmlFor="answerSort">Sorted by:</label>
              <div>Trending sort available</div>
            </div>
            <div className="sortTypeContainer">
              <select name="sortType" id="answerSort">
                {/* select 추가 예정 */}
                <option value="high">Highest score (default)</option>
                <option value="Trend">
                  Trending (recent votes count more)
                </option>
                <option value="newest">Date modified (newest first)</option>
                <option value="oldest">Date created (oldest first)</option>
              </select>
            </div>
          </SeletContainer>
        </SortMenu>
        <section className="answersList">
          {answers
            ? answers.map((answer) => (
                <ViewContainer key={answer.answerId} data={answer} />
              ))
            : null}
        </section>
      </section>
      <section className="addAnswerContainer">
        <h3>Your Answer</h3>
        <TextEditer></TextEditer> {/* 인풋값 추출 필요 */}
        <BlueButton onClick={handleSubmit}>Post Your Answer</BlueButton>
      </section>
    </>
  );
};
export default Answer;

import { useSelector, useDispatch } from 'react-redux';
import { addAnswer } from '../../slices/postSlice';
import { inputAction } from '../../slices/inputAnswerSlice';
import styled from 'styled-components';
import BlueButton from '../Bluebutton';
import ViewContainer from './ViewContainer';
import Textarea from '../CommonLayout/Textarea';

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
  const { value } = useSelector((state) => state.inputAnswer);
  const { answers, questionId } = useSelector((state) => state.singlePost);

  const handleSubmit = (event) => {
    event.preventDefault();
    const dataForThunk = {
      url: `/answers/${questionId}/add` /* url변경 필요 */,
      requestbody: value,
    };
    dispatch(addAnswer(dataForThunk));
    dispatch(inputAction.fill(''));
  };
  const handleInput = (event) => {
    dispatch(inputAction.fill(event.target.value));
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
        <Textarea onChange={handleInput} value={value}></Textarea>
        <BlueButton onClick={handleSubmit}>Post Your Answer</BlueButton>
      </section>
    </>
  );
};
export default Answer;

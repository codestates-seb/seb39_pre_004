import { useSelector, useDispatch } from 'react-redux';
import { addAnswer } from '../../slices/postSlice';
import { inputAction } from '../../slices/inputSlice';
import styled from 'styled-components';
import BlueButton from '../Bluebutton';
import ViewContainer from './ViewContainer';
import Textarea from '../CommonLayout/Textarea';

// const AnswerHead = styled.section``;
const SortMenu = styled.div`
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  & > :first-child {
    font-size: 1.2rem;
    font-weight: 500;
  }
`;
const SeletContainer = styled.div`
  display: flex;
  gap: 5px;

  & > :first-child {
    font-size: 0.8rem;
    font-weight: 400;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    .orange {
      color: var(--orange);
    }
  }
  select {
    padding: 5px;
    font-size: 0.8rem;
    border: 1px solid var(--gray);
    border-radius: 3px;
  }
`;
const H3 = styled.h3`
  font-weight: 500;
  margin-top: 1.5rem;
  margin-bottom: 15px;
`;
const Hr = styled.hr`
  border: 0.2px solid var(--gray-bar);
`;

const Answer = () => {
  const dispatch = useDispatch();
  const { value } = useSelector((state) => state.input);
  const { answers, questionId } = useSelector((state) => state.singlePost);

  const handleSubmit = (event) => {
    event.preventDefault();
    const dataForThunk = {
      url: `/answers/${questionId}/add` /* url변경 필요 */,
      requestbody: value,
    };
    dispatch(addAnswer(dataForThunk));
    dispatch(inputAction.answer(''));
  };
  const handleInput = (event) => {
    dispatch(inputAction.answer(event.target.value));
  };

  return (
    <>
      <section className="viewAnswer">
        <SortMenu>
          <div>{answers.length} Answers</div>
          <SeletContainer>
            <div>
              <label htmlFor="answerSort">Sorted by:</label>
              <div className="orange">Trending sort available</div>
            </div>
            <select name="sortType" id="answerSort">
              <option value="high">Highest score (default)</option>
              <option value="Trend">Trending (recent votes count more)</option>
              <option value="newest">Date modified (newest first)</option>
              <option value="oldest">Date created (oldest first)</option>
            </select>
          </SeletContainer>
        </SortMenu>
        <section className="answersList">
          {answers
            ? answers.map((answer) => (
                <div key={`${answer.answerId}`}>
                  <ViewContainer data={answer} />
                  <Hr />
                </div>
              ))
            : null}
        </section>
      </section>
      <section className="addAnswerContainer">
        <H3>Your Answer</H3>
        <Textarea onChange={handleInput} value={value}></Textarea>
        <BlueButton onClick={handleSubmit}>Post Your Answer</BlueButton>
      </section>
    </>
  );
};
export default Answer;

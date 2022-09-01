import { useSelector } from 'react-redux';
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
  const store = useSelector((state) => state.singlePost);
  const { answers } = store;
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
      <section className="addAnswer">
        <h3>Your Answer</h3>
        <TextEditer></TextEditer>
        <BlueButton>Post Your Answer</BlueButton>
      </section>
    </>
  );
};
export default Answer;

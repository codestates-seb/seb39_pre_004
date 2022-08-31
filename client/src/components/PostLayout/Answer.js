import styled from 'styled-components';
import BlueButton from '../Bluebutton';
import ViewContainer from './ViewContainer';

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
  return (
    <>
      <section className="viewAnswer">
        <SortMenu>
          <div>{2} Answers</div>
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
          {/* AnswerList data에 map 적용 예정 */}
          <ViewContainer isAnswer={true} />
        </section>
      </section>
      <section className="addAnswer">
        <h3>Your Answer</h3>
        {/* text editor 컴포넌트 적용 */}
        <div>text editer space</div>
        <BlueButton>Post Your Answer</BlueButton>
      </section>
    </>
  );
};
export default Answer;

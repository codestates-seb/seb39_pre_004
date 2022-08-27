import ViewContainer from './ViewContainer';
const Answer = () => {
  return (
    <>
      <section className="viewAnswer">
        <div className="sortMenu">
          <div>{2} Answers</div>
          <div>
            <div>
              <lable htmlFor="answerSort">Sorted by:</lable>
              <div id="answerSort">Trending sort available</div>
            </div>
            <div className="sortType">
              <select name="">
                {/* option value 추가 필요 */}
                <option selected>Highest score (default)</option>
                <option>Trending (recent votes count more)</option>
                <option>Date modified (newest first)</option>
                <option>Date created (oldest first)</option>
              </select>
            </div>
          </div>
        </div>
        <section className="answersList">
          {/* AnswerList data에 map 적용 예정 */}
          <ViewContainer isAnswer={true} />
        </section>
      </section>
      <section className="addAnswer">
        <h3>Your Answer</h3>
        {/* text editor 컴포넌트 적용 */}
        <div>text editer space</div>
        <button>Post Your Answer</button>
      </section>
    </>
  );
};
export default Answer;

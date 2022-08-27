import ViewContainer from './ViewContainer';
const Answer = () => {
  return (
    <>
      <section className="viewAnswer">
        <div className="sortMenu">
          <div>{2} Answers</div>
          <div>답변 정렬 영역</div>
        </div>
        <section className="answersList">
          {/* AnswerList data에 map 적용 예정 */}
          <ViewContainer isAnswer={true} />
        </section>
      </section>
      <section className="addAnswer">
        <h3>Your Answer</h3>
        <div>text editer space</div>
        <button>Post Your Answer</button>
      </section>
    </>
  );
};
export default Answer;

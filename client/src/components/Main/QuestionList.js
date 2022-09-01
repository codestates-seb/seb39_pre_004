import QuestionRow from './QuestionRow';

const QuestionList = ({ mainData }) => {
  return (
    <div>
      {mainData.map((question) => (
        <QuestionRow
          key={question.questionId}
          id={question.questionId}
          question={question}
        />
      ))}
    </div>
  );
};

export default QuestionList;

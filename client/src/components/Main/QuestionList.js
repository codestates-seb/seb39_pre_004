import QuestionRow from './QuestionRow';

const QuestionList = ({ questions }) => {
  return (
    <div>
      {questions.question.map((question) => (
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

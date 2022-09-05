import styled from 'styled-components';
import { Link } from 'react-router-dom';

const RowContainer = styled.div`
  display: flex;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid var(--gray);
`;

const QuestionStatus = styled.div`
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  gap: 0.5rem;
  font-size: 0.8rem;
  text-align: right;
`;

const QuestionContent = styled.div`
  width: 100vw;
  padding: 0 0 0 30px;
  .main-title {
    margin-top: 10px;
    color: var(--deep-blue);
    font-weight: normal;
    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-word; // 두 줄 이상
    display: -webkit-box;
    -webkit-line-clamp: 1; // 원하는 라인수
    -webkit-box-orient: vertical;
  }

  .main-body {
    color: var(--deep-gray);
    font-size: 0.9rem;
    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-word; // 두 줄 이상
    display: -webkit-box;
    -webkit-line-clamp: 2; // 원하는 라인수
    -webkit-box-orient: vertical;
  }
`;

const AuthorInfoBlock = styled.div`
  width: 175px;
  margin-top: 5px;
  display: flex;
  gap: 0.5rem;
  align-items: center;
  margin-left: auto;
  font-size: 0.8rem;
  & span {
    color: var(--dark-gray);
    vertical-align: 3px;
  }
`;

const QuestionRow = ({ id, question }) => {
  return (
    <RowContainer>
      <QuestionStatus>
        <span>{question.likes} likes</span>
        <span>{question.answers} answers</span>
        <span>{question.views} views</span>
      </QuestionStatus>
      <QuestionContent>
        <Link to={`/questions/${id}`}>
          <h3 className="main-title">{question.title}</h3>
        </Link>
        <div className="main-body">{question.body}</div>
        <AuthorInfoBlock>
          <Link to={`/users/`}>
            <img
              src="https://www.gravatar.com/avatar/6ac74e9b9526ea2907458a196ef26d15?s=32&amp;d=identicon&amp;r=PG&amp;f=1"
              alt="user avatar"
              width="16"
              height="16"
            />
            <span>{question.owner.name}</span>
          </Link>
          <span>{question.createDate}</span>
        </AuthorInfoBlock>
      </QuestionContent>
    </RowContainer>
  );
};

export default QuestionRow;

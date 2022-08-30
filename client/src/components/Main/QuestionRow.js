import styled from 'styled-components';
import { Link } from 'react-router-dom';

const RowContainer = styled.div`
  display: flex;
  align-items: center;
  height: 100px;
  margin-top: 20px;
  border-bottom: 1px solid var(--gray);
`;

const QuestionStatus = styled.div`
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
`;

const QuestionContent = styled.div`
  padding: 0 30px;
  h3 {
    font-weight: normal;
    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-word; // 두 줄 이상
    display: -webkit-box;
    -webkit-line-clamp: 1; // 원하는 라인수
    -webkit-box-orient: vertical;
  }

  div {
    text-overflow: ellipsis;
    overflow: hidden;
    word-break: break-word; // 두 줄 이상
    display: -webkit-box;
    -webkit-line-clamp: 2; // 원하는 라인수
    -webkit-box-orient: vertical;
  }
`;

const AuthorInfoBlock = styled.div`
  width: 170px;
  margin-top: 5px;
  display: flex;
  align-items: center;
  margin-left: auto;
  font-size: 13px;
`;

const QuestionRow = () => {
  return (
    <RowContainer>
      <QuestionStatus>
        <span>0 votes</span>
        <span>1 answers</span>
        <span>6 views</span>
      </QuestionStatus>
      <QuestionContent>
        <Link to="/questions">
          <h3>IN memory Graph DB with specific requirements</h3>
        </Link>
        <div>
          We are looking for some in memory graph data kind of solution . Where
          we can store the graph data like the below
        </div>
        <AuthorInfoBlock>
          <Link to="/users">
            <img
              src="https://www.gravatar.com/avatar/6ac74e9b9526ea2907458a196ef26d15?s=32&amp;d=identicon&amp;r=PG&amp;f=1"
              alt="user avatar"
              width="16"
              height="16"
            />
            <span>StringMain</span>
          </Link>
          <span>2022-08-30</span>
        </AuthorInfoBlock>
      </QuestionContent>
    </RowContainer>
  );
};

export default QuestionRow;

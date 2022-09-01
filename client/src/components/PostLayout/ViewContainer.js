import styled from 'styled-components';
import { Link } from 'react-router-dom';
import CommentContainer from './CommentContainer';

export const TextButton = ({ text }) => {
  return <button>{text}</button>;
};

const Container = styled.div`
  display: flex;
  flex-direction: row;
`;
const Menu = styled.section`
  display: flex;
  flex-direction: column;
`;
const QuestionBody = styled.section`
  flex: 1;
`;
const Div = styled.div`
  display: flex;
  justify-content: space-between;
  .controllButtons {
    flex-grow: 1;
  }
  .userInfoContainer {
    display: flex;
    flex-direction: column;
    .userInfo {
      display: flex;
    }
  }
`;

const ViewContainer = ({ data, isAnswer }) => {
  return (
    <Container>
      <Menu>
        <i className="fa-solid fa-angle-up"></i>
        {data.likes}
        <i className="fa-solid fa-chevron-down"></i>
        {!isAnswer ? (
          <i className="fa-solid fa-bookmark"></i>
        ) : (
          <i className="fa-solid fa-check"></i>
        )}
      </Menu>
      <QuestionBody>
        <div>{data.body}</div>
        <div>
          {data.tag
            ? data.tag.map((el, idx) => (
                /*CSS 컴포넌트 분리*/
                <div key={idx}>{el}</div>
              ))
            : null}
        </div>
        <Div>
          <div className="controllButtons">
            {!isAnswer ? (
              <>
                <Link to="/edit">
                  <TextButton text="Edit" />
                </Link>
                <Link to="/">
                  <TextButton text="Delete" />
                </Link>
              </>
            ) : (
              <>
                <TextButton text="Edit" />
                <TextButton text="Delete" />
              </>
            )}
          </div>
          <div className="userInfoContainer">
            <div>asked {data.modDate}</div>
            <div className="userInfo">
              <img src={data.image} alt="userImage" />
              {/* userImage */}
              <div>
                <div>{data.owner.name}</div>
                <div>{1}</div>
              </div>
            </div>
            <div>New contributor</div>
          </div>
        </Div>
        <CommentContainer />
        {/* 토글용 key 필요 */}
      </QuestionBody>
    </Container>
  );
};
export default ViewContainer;

import styled from 'styled-components';
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

const ViewContainer = ({ isAnswer }) => {
  return (
    <Container>
      <Menu>
        <i className="fa-solid fa-angle-up"></i>
        {0}
        <i className="fa-solid fa-chevron-down"></i>
        {!isAnswer ? (
          <i className="fa-solid fa-bookmark"></i>
        ) : (
          <i className="fa-solid fa-check"></i>
        )}
      </Menu>
      <QuestionBody>
        <div>{'question body'}</div>
        <div>tags</div>
        <Div>
          <div className="controllButtons">
            <TextButton text="Edit" />
            <TextButton text="Delete" />
          </div>
          <div className="userInfoContainer">
            <div>asked {'23 minite ago'}</div>
            <div className="userInfo">
              <div>userImage</div>
              {/* <img
              src="https://images.unsplash.com/photo-1633332755192-727a05c4013d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8dXNlcnxlbnwwfHwwfHw%3D&w=1000&q=80"
              alt="작성자 정보"
            /> */}
              <div>
                <div>{'userName'}</div>
                <div>{1}</div>
              </div>
            </div>
            <div>New contributor</div>
          </div>
        </Div>
        <CommentContainer />
      </QuestionBody>
    </Container>
  );
};
export default ViewContainer;

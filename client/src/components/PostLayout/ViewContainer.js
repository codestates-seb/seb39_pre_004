// import { useSelector, useDispatch } from 'react-redux';
import { useDispatch } from 'react-redux';
import { useNavigate, Link } from 'react-router-dom';
import { deleteSomething } from '../../slices/postSlice';
import styled from 'styled-components';
import CommentContainer from './CommentContainer';

export const TextButton = styled.button`
  color: gray;
  background-color: transparent;
`;

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

const ViewContainer = ({ data }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const dataForDelete = {
    target: '',
    url: '',
  };
  const handelDeleteQestion = () => {
    dataForDelete.target = 'question';
    dataForDelete.url = `${data.questionId}/`;
    // dispatch(deleteSomething(`questions/${data.questionId}/`));
    // dispatch(deleteSomething(`${data.questionId}/`));
    dispatch(deleteSomething(dataForDelete));

    navigate('/');
  };
  const handelDeleteAnswer = () => {
    dataForDelete.target = 'answer';
    dataForDelete.url = `${data.answerId}/`;
    dispatch(deleteSomething(dataForDelete));
  };
  return (
    <Container>
      <Menu>
        <i className="fa-solid fa-angle-up"></i>
        {data.likes}
        <i className="fa-solid fa-chevron-down"></i>
        {data.answers ? (
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
            {data.answers ? (
              <>
                <Link to="/edit">
                  <TextButton>Edit</TextButton>
                </Link>

                <TextButton onClick={handelDeleteQestion}>Delete</TextButton>
              </>
            ) : (
              <>
                <TextButton>Edit</TextButton>
                <TextButton onClick={handelDeleteAnswer}>Delete</TextButton>
              </>
            )}
          </div>
          <div className="userInfoContainer">
            <div>asked {data.modDate}</div>
            <div className="userInfo">
              <img src={data.image} alt="userImage" />
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

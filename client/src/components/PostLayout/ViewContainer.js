// import { useSelector, useDispatch } from 'react-redux';
import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate, Link } from 'react-router-dom';
import { deleteSomething, editAnswer } from '../../slices/postSlice';
import styled from 'styled-components';
import CommentContainer from './CommentContainer';
import Tag from '../Tag';
import TextButton from '../TextButton';

const Container = styled.div`
  width: 100%;
  margin-top: 1rem;
  margin-bottom: 1rem;
  display: flex;
  flex-direction: row;
  gap: 10px;
`;
const Menu = styled.section`
  width: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.8rem;
`;
const QuestionBody = styled.section`
  width: 100%;
  flex: 1;
  margin-right: 10px;
  & > div:first-child {
    width: 100%;
    margin-bottom: 10px;
    word-break: break-word;
  }
  textarea {
    width: 100%;
    min-height: 100px;
    border: 1px solid var(--gray);
    border-radius: 3px;
    padding: 10px;
    resize: none;
  }
`;
const TagContainer = styled.div`
  margin-bottom: 0.7rem;
  display: flex;
  align-items: center;
  gap: 3px;
`;
const Div = styled.div`
  display: flex;
  justify-content: space-between;
  .controllButtons {
    flex-grow: 1;
    display: flex;
    align-items: baseline;
    gap: 10px;
  }
`;
const UserInfoContainer = styled.div`
  background-color: var(--blue-bg);
  flex-basis: 130px;
  height: 65px;
  padding: 0.5rem;
  border-radius: 3px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  & > :first-child {
    color: var(--deep-gray);
    font-size: 0.75rem;
    font-weight: 500;
    margin-bottom: 3px;
  }
  .userInfo {
    margin: 0;
    display: flex;
    align-items: center;
    gap: 10px;
  }
  img {
    width: 33px;
    height: 33px;
    border-radius: 3px;
  }
  .useName {
    color: var(--deep-blue);
    font-size: 0.85rem;
    margin: 0;
  }
`;

const ViewContainer = ({ data }) => {
  const [isEditMode, setEditMode] = useState(false);
  const [answerBody, setAnswerBody] = useState(data.body);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handelEditMode = () => {
    setEditMode(true);
  };

  const handelAnswerSave = (event) => {
    event.preventDefault();
    const dataForEdit = {
      url: `/answers/${data.answerId}/edit`,
      requestbody: answerBody,
    };
    dispatch(editAnswer(dataForEdit));
    setEditMode(false);
  };

  const dataForDelete = {
    target: '',
    url: '',
  };
  const handelDeleteQestion = () => {
    dataForDelete.target = 'question';
    dataForDelete.url = `/questions/${data.questionId}/`;
    dispatch(deleteSomething(dataForDelete));
    navigate('/');
  };
  const handelDeleteAnswer = () => {
    dataForDelete.target = 'answer';
    dataForDelete.url = `/answers/${data.answerId}/`;
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
        {isEditMode ? (
          <textarea
            value={answerBody}
            onChange={(event) => {
              setAnswerBody(event.target.value);
            }}
          ></textarea>
        ) : (
          <div>{answerBody}</div>
        )}

        <TagContainer>
          {/* 임의로 추가한 태그로 DB에서 사용할 수 있으면 지우겠습니다. */}
          <Tag>javascript</Tag>
          <Tag>javascript</Tag>
          <Tag>javascript</Tag>
          {data.tag
            ? data.tag.map((el, idx) => (
                /*CSS 컴포넌트 분리*/
                <Tag key={idx}>{el}</Tag>
              ))
            : null}
        </TagContainer>
        <Div>
          <div className="controllButtons">
            {data.answers ? (
              <>
                <Link to={`/questions/${data.questionId}/edit`}>
                  <TextButton>Edit</TextButton>
                </Link>

                <TextButton onClick={handelDeleteQestion}>Delete</TextButton>
              </>
            ) : (
              <>
                {isEditMode ? (
                  <TextButton onClick={handelAnswerSave}>Save</TextButton>
                ) : (
                  <TextButton onClick={handelEditMode}>Edit</TextButton>
                )}
                <TextButton onClick={handelDeleteAnswer}>Delete</TextButton>
              </>
            )}
          </div>
          <UserInfoContainer>
            <div>asked {data.modDate.slice(0, 10)}</div>
            <div className="userInfo">
              <img
                src="https://images.unsplash.com/photo-1633332755192-727a05c4013d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1400&q=60"
                alt="userImage"
              />
              {/* <img src={data.image} alt="userImage" /> */}
              <div className="useName">{data.owner.name}</div>
            </div>
          </UserInfoContainer>
        </Div>
        <CommentContainer
          data={data}
          type={data.answers ? 'question' : 'answer'}
        />
      </QuestionBody>
    </Container>
  );
};
export default ViewContainer;

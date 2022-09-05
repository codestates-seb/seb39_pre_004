import { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { addComment } from '../../slices/postSlice';
import { inputAction } from '../../slices/inputSlice';
import styled from 'styled-components';
import Bluebutton from '../Bluebutton';
import { TextButton } from './ViewContainer';

const CommentList = styled.ul`
  display: flex;
  flex-direction: column;
  li {
    display: flex;
    flex-direction: row;
  }
`;
const CommentToggle = styled.section`
  display: flex;
  & > :first-child {
    flex: 1;
  }
  textarea {
    width: 100%;
  }
`;

const CommentContainer = ({ type, data }) => {
  const dispatch = useDispatch();
  const [isChecked, setChecked] = useState(false);
  const { commentValue } = useSelector((state) => state.input);

  const handleEditContainer = () => {
    setChecked(!isChecked);
  };
  const handleWrite = (event) => {
    dispatch(inputAction.comment(event.target.value));
  };

  let pathForAddComment;
  if (data.questionId) {
    pathForAddComment = `/questions/${data.questionId}/comments`;
  }
  if (data.answerId) {
    pathForAddComment = `/answers/${data.answerId}/comments`;
  }
  ///answers/1/comments
  const dataForCommentThunk = {
    url: pathForAddComment,
    requestbody: commentValue,
    id: data.questionId || data.answerId,
    type,
  };
  const submitComment = (event) => {
    event.preventDefault();
    dispatch(addComment(dataForCommentThunk));
    dispatch(inputAction.comment(''));
  };

  return (
    <>
      <CommentList>
        {data.comments.length > 0 &&
          data.comments.map((comment) => {
            return (
              <li key={comment.questionCommentId || comment.answerCommentId}>
                <p>{comment.body}</p>
                <div>{comment.owner.name}</div>
                <div>{comment.createDate}</div>
                {/* 작성자라면 Delete 버튼 노출 -> 코멘트 수정창 렌더링 */}
                <TextButton>Delete</TextButton>
              </li>
            );
          })}
      </CommentList>
      {isChecked ? (
        <CommentToggle>
          <div className="commentInputArea">
            <textarea onChange={handleWrite} value={commentValue} />
            <div>{'Enter at least 15 charactors'}</div>
          </div>
          <Bluebutton onClick={submitComment}>Add comment</Bluebutton>
        </CommentToggle>
      ) : null}
      <button onClick={handleEditContainer}>Add a comment</button>
    </>
  );
};
export default CommentContainer;

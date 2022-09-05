import { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { addComment } from '../../slices/postSlice';
import { inputAction } from '../../slices/inputSlice';
import styled from 'styled-components';
import BlueButton from '../Bluebutton';
import TextButton from '../TextButton';

const AddBtn = styled(BlueButton)`
  /* padding: */
  /* height: 100%; */
`;
const TextBtnComment = styled(TextButton)`
  color: var(--gray-text);
  margin-top: 10px;
`;

const CommentList = styled.ul`
  font-size: 0.8rem;
  margin-top: 10px;
  padding-top: 3px;
  display: flex;
  flex-direction: column;
  li {
    border-top: 0.2px solid var(--gray-bar);
    border-bottom: 0.2px solid var(--gray-bar);
    padding: 9px 15px 6px 15px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: baseline;
    gap: 3px;
    word-break: break-word;
    & > p {
      font-size: 0.9rem;
    }
    .block {
      background-color: var(--blue-bg);
      color: var(--deep-blue);
      padding: 4px 5px 2px 5px;
      border-radius: 3px;
    }
    & > div:last-child {
      color: var(--dark-gray);
    }
  }
`;

const CommentToggle = styled.section`
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  gap: 5px;

  .commentInputArea {
    font-size: 0.9rem;
    display: flex;
    flex-direction: column;
  }

  textarea {
    /* width: 100%; */
    /* height: 80px; */
    /* border: 1px solid var(--gray); */
    /* border-radius: 3px; */
    /* resize: none; */
    /* padding: 10px; */
    /* flex-basis: 1; */
    /* height: 80px; */
    /* width: 100%; */
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
                <span>-</span>
                <div className="block">{comment.owner.name}</div>
                <div>{comment.createDate}</div>
                {/* 작성자라면 Delete 버튼 노출 -> 코멘트 수정창 렌더링 */}
                {/* <TextBtnComment>Delete</TextBtnComment> */}
              </li>
            );
          })}
      </CommentList>
      {isChecked ? (
        <CommentToggle>
          <div className="commentInputArea">
            <textarea onChange={handleWrite} value={commentValue} />
            <div>Enter at least 15 charactors</div>
          </div>
          <AddBtn onClick={submitComment}>Add comment</AddBtn>
        </CommentToggle>
      ) : null}
      <TextBtnComment onClick={handleEditContainer}>
        Add a comment
      </TextBtnComment>
    </>
  );
};
export default CommentContainer;

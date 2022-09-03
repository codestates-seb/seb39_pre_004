import { useSelector, useDispatch } from 'react-redux';
import { commentActions } from '../../slices/commentSlice';
import styled from 'styled-components';
import Bluebutton from '../Bluebutton';
// import { TextButton } from './ViewContainer';

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

const CommentContainer = ({ type, comments }) => {
  const dispatch = useDispatch();
  const isChecked = useSelector((state) => state.comment.checked);

  const handleComment = () => {
    dispatch(commentActions.setChecked());
  };

  console.log('코멘트소속, eslint 때문에 지우지 말아주세요', type);
  return (
    <>
      <CommentList>
        {comments.length > 0 &&
          comments.map((comment) => {
            return (
              <li key={comment.questionCommentId || comment.answerCommentId}>
                <p>{comment.body}</p>
                <div>{comment.owner.name}</div>
                <div>{comment.createDate}</div>
                {/* 작성자라면 Delete 버튼 노출 -> 코멘트 수정창 렌더링 */}
              </li>
            );
          })}
      </CommentList>
      {isChecked ? (
        <CommentToggle>
          <div className="commentInputArea">
            <textarea />
            <div>{'Enter at least 15 charactors'}</div>
          </div>
          <Bluebutton>Add comment</Bluebutton>
        </CommentToggle>
      ) : null}
      <button onClick={handleComment}>Add a comment</button>
      {/* <TextButton text="Add a comment" onClick={handleComment} /> */}
    </>
  );
};
export default CommentContainer;

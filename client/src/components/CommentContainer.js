import styled from 'styled-components';
import Bluebutton from './Bluebutton';
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

const CommentContainer = () => {
  return (
    <>
      <CommentList>
        {/* map적용 */}
        <li>
          <p>{'commentContent'}</p>
          <div>{'commentUser'}</div>
          <div>{'23 hours ago'}</div>
          {/* 작성자라면 Edit버튼 노출 -> 코멘트 수정창 렌더링 */}
        </li>
      </CommentList>
      <CommentToggle>
        <div className="commentInputArea">
          <textarea />
          <div>{'Enter at least 15 charactors'}</div>
        </div>
        <Bluebutton>Add comment</Bluebutton>
      </CommentToggle>
      <TextButton text="Add a comment" />
    </>
  );
};
export default CommentContainer;

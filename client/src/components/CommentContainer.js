import { TextButton } from './ViewContainer';
const CommentContainer = () => {
  return (
    <div>
      <ul className="commentList">
        <li>
          <p>{'commentContent'}</p>
          <div>{'commentUser'}</div>
          <div>{'23 hours ago'}</div>
        </li>
      </ul>
      <div className="commentInputArea">
        <textarea></textarea>
        <div>{'Enter at least 15 charactors'}</div>
      </div>
      <TextButton text="Add a comment" />
    </div>
  );
};
export default CommentContainer;

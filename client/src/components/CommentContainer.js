import { TextButton } from './ViewContainer';
const CommentContainer = () => {
  return (
    <>
      <ul className="commentList">
        <li>
          <p>{'commentContent'}</p>
          <div>{'commentUser'}</div>
          <div>{'23 hours ago'}</div>
        </li>
      </ul>
      <section>
        <div className="commentInputArea">
          <textarea />
          <div>{'Enter at least 15 charactors'}</div>
        </div>
        {/* <BlueButton text="Add comment" /> */}
      </section>
      <TextButton text="Add a comment" />
    </>
  );
};
export default CommentContainer;

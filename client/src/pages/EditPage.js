import { useSelector, useDispatch } from 'react-redux';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { editFetch } from '../slices/editSlice';
import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import BlueButton from '../components/Bluebutton';
import Textarea from '../components/CommonLayout/Textarea';
import styled from 'styled-components';

export const TagsInput = styled.div`
  margin: 5px 0 20px;
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  min-height: 48px;
  width: 100%;
  padding: 0 8px;
  border: 1px solid rgb(214, 216, 218);
  border-radius: 6px;
  > ul {
    display: flex;
    flex-wrap: wrap;
    padding: 0;
    margin: 8px 0 0 0;
    > .tag {
      width: auto;
      height: 32px;
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 0 8px;
      font-size: 14px;
      list-style: none;
      border-radius: 6px;
      margin: 0 8px 8px 0;
      background: #e1ecf4;
      > .tag-title {
        color: var(--deep-blue);
      }
      > .tag-close-icon {
        display: block;
        width: 16px;
        height: 16px;
        line-height: 16px;
        text-align: center;
        font-size: 14px;
        margin-left: 8px;
        color: var(--deep-blue);
        border-radius: 50%;
        background: #fff;
        cursor: pointer;
      }
    }
  }
  > input {
    flex: 1;
    border: none;
    height: 46px;
    font-size: 14px;
    padding: 4px 0 0 0;
    :focus {
      outline: transparent;
    }
  }
`;

const Edit = () => {
  const store = useSelector((state) => state.singlePost);
  const { questionId, title, body } = store;

  const [editTitle, setEditTitle] = useState(title);
  const [editBody, setEditBody] = useState(body);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    const editRequest = {
      url: `/questions/${questionId}/edit`,
      title: editTitle,
      body: editBody,
    };
    dispatch(editFetch(editRequest));
    navigate(`/questions/${questionId}`);
  };

  return (
    <>
      <Subtitle>Title</Subtitle>
      <Input
        placeholder="e.g is threr an R function for finding the index if an element in a vector"
        value={editTitle}
        onChange={(e) => setEditTitle(e.target.value)}
      />

      <Subtitle>Body</Subtitle>
      <Textarea
        value={editBody}
        onChange={(e) => setEditBody(e.target.value)}
      />
      <Subtitle>Tags</Subtitle>
      <BlueButton onClick={handleSubmit}>Save edits</BlueButton>
    </>
  );
};

export default Edit;

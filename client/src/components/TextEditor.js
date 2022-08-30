import { useRef } from 'react';
import { Editor } from '@toast-ui/react-editor';
import styled from 'styled-components';
import '@toast-ui/editor/dist/toastui-editor.css';

const Container = styled.div`
  margin-bottom: 30px;
`;

const ToastEditor = () => {
  const editorRef = useRef();

  const handleChangeInput = () => {
    const mark_data = editorRef.current.getInstance().getMarkdown();
    const html_data = editorRef.current.getInstance().getHTML();
    console.log(mark_data);
    console.log(html_data);
  };

  return (
    <Container>
      <Editor
        placeholder="내용을 입력해주세요."
        previewStyle="vertical"
        initialValue={' '}
        height="350px"
        initialEditType="WYSIWYG"
        ref={editorRef}
        onChange={handleChangeInput}
        useCommandShortcut={false}
      ></Editor>
    </Container>
  );
};

export default ToastEditor;

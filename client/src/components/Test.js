import { useEffect } from 'react';
// 헬스 체크를 위한 컴포넌트입니다.
const Test = () => {
  useEffect(() => {
    fetch('/api/hello').then((res) => console.log(res));
  }, []);
};

export default Test;

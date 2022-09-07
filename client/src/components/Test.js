import { useEffect } from 'react';
import axios from 'axios';
// 헬스 체크를 위한 컴포넌트입니다.

const Test = () => {
  useEffect(() => {
    // axios
    //   .post('/test2', {
    //     title: '타이틀',
    //   })
    //   .then(function (response) {
    //     console.log(response);
    //   })
    //   .catch(function (error) {
    //     console.log(error);
    //   });
    axios.get('/main').then(function (res) {
      console.log(res.data);
    });
  }, []);
};

export default Test;

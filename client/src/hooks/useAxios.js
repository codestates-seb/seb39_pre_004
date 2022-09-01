import axios from 'axios';
import { useState, useEffect } from 'react';

const useAxios = (url) => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setTimeout(() => {
      axios
        .get(url)
        .then((res) => {
          if (res.status !== 200) {
            // 서버에서 받은 error
            throw Error('데이터를 가져올 수 없습니다.');
          }
          return res;
        })
        .then((data) => {
          setIsPending(false);
          setData(data.data);
          setError(null);
        })
        .catch((err) => {
          if (err.name === 'AbortError') {
            console.log('fetch aborted');
          } else {
            // 네트워크, 커넥션 에러를 자동으로 catch
            setIsPending(false);
            setError(err.message);
          }
        });
    }, 1000);
  }, [url]);

  return { data, isPending, error };
};

export default useAxios;

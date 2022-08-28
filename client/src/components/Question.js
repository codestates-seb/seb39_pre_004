import ViewContainer from './ViewContainer';

const InfoPost = ({ keyword, value }) => {
  return (
    <>
      <span>{keyword}</span>
      <span>{value}</span>
    </>
  );
};

const Question = () => {
  return (
    <>
      <header>
        <h2>{'question title'}</h2>
        <div>
          <InfoPost keyword={'Asked'} value={'today'} />
          <InfoPost keyword={'Modified'} value={'todat'} />
          <InfoPost keyword={'Views'} value={`${5} times`} />
        </div>
      </header>
      <ViewContainer isAnswer={false} />
    </>
  );
};
export default Question;

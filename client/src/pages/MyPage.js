import styled from 'styled-components';
import SubHeader from '../components/SubHeader';
import Subtitle from '../components/Subtitle';
import Input from '../components/Input';
import Textarea from '../components/CommonLayout/Textarea';
import BlueButton from '../components/Bluebutton';

const Profile = styled.div`
  display: flex;
`;

const UserInfo = styled.div`
  font-size: 2rem;
  margin-left: 20px;
`;

const ProfileImage = styled.div`
  width: 150px;
  height: 150px;
  margin-bottom: 20px;
  background: #eee;
  border-radius: 3px;
`;

const InfoTitle = styled.div`
  font-size: 1.2rem;
  padding-top: 15px;
`;

const ProfileEdit = styled.div`
  margin: 15px 0 30px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 3px;
`;

const Users = () => {
  return (
    <>
      <Profile>
        <ProfileImage></ProfileImage>
        <UserInfo>
          <div>team4</div>
          <p>안녕하세요</p>
        </UserInfo>
      </Profile>
      <SubHeader>Edit your profile</SubHeader>
      <InfoTitle>Public information</InfoTitle>
      <ProfileEdit>
        <Subtitle>Profile image</Subtitle>
        <ProfileImage></ProfileImage>
        <Subtitle>Display name</Subtitle>
        <Input
          type="text"
          placeholder="e.g is threr an R function for finding the index if an element in a vector"
        />
        <Subtitle>About me</Subtitle>
        <Textarea />
      </ProfileEdit>
      <BlueButton>Save profile</BlueButton>
    </>
  );
};
export default Users;

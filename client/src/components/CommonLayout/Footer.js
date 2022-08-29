import styled from 'styled-components';

const FooterBlock = styled.div`
  padding: 32px 12px 12px 12px;
  display: flex;
  flex-direction: row;
  background: var(--black);
  color: var(--white);
  position: absolute;
  width: 100vw;
  height: 322px;
  div {
    width: 100%;
  }
  ul {
    padding: 10px;
    display: block;
  }
  .footer-col {
    display: flex;
  }
`;

const Footer = () => {
  return (
    <footer>
      <FooterBlock>
        <div>
          <svg aria-hidden="true" width="32" height="37" viewBox="0 0 32 37">
            <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB"></path>
            <path
              d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
              fill="#F48024"
            ></path>
          </svg>
        </div>
        <div className="footer-col">
          <div className="col">
            <ul>
              <h5>STACK OVERFLOW</h5>
              <li>Ouestions</li>
              <li>Help</li>
            </ul>
          </div>
          <div>
            <ul>
              <h5>PRODUCTS</h5>
              <li>Teams</li>
              <li>Advertising</li>
              <li>Collectives</li>
              <li>Talent</li>
            </ul>
          </div>
          <div>
            <ul>
              <h5>COMPANY</h5>
              <li>About</li>
              <li>Press</li>
              <li>Work Here</li>
              <li>Privacy Policy</li>
            </ul>
          </div>
        </div>
        <div>
          Site design / logo © 2022 Stack Exchange Inc; user contributions
          licensed under CC BY-SA. rev 2022.8.26.42925
        </div>
      </FooterBlock>
    </footer>
  );
};

export default Footer;

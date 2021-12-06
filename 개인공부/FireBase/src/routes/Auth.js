import React from "react";
import { authService, firebaseInstance } from "fbase";
import SignInForm from "components/SignInForm";
import TwitterIcon from '@material-ui/icons/Twitter';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faTwitter,
  faGoogle,
  faGithub,
} from "@fortawesome/free-brands-svg-icons";

const Auth=() => {
  const onSocialClick = async(event) => {
    const {target:{name}} = event;
    let provider;
    if(name === "google"){
      provider = new firebaseInstance.auth.GoogleAuthProvider();
    } else if(name = "github"){
      provider = new firebaseInstance.auth.GIthubAuthProvider();
    }
    await authService.signInWithPopup(provider) ;
  }
  return (
    <div className="authWapper">
      <FontAwesomeIcon
        icon={faTwitter}
        color={"#04AAFF"}
        size="3x"
        style={{ marginBottom: 30 }}
        className="logo"
      />
      <SignInForm/>
      <div onClick={console.log('hi')} className="authSpan">회원가입</div>
      <div className="socialWrap">
        <button onClick={onSocialClick} name="google">Continue with <FontAwesomeIcon icon={faGoogle} /></button>
        <button onClick={onSocialClick} name="github">Continue with <FontAwesomeIcon icon={faGithub} /></button >
      </div>
    </div>
  );
};
export default Auth;
import React, { useState } from "react";
import { authService, firebaseInstance } from "fbase";
import SignInForm from "components/SignInForm";
import SignUpForm from "components/SignUpForm";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faTwitter,
  faGoogle,
  faGithub,
} from "@fortawesome/free-brands-svg-icons";

const Auth=() => {

  const [newAccount, setNewAccount] = useState(false);
  
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

  const toggleAccount = () => setNewAccount((prev) => !prev);

  return (
    <div className="authWapper">
      <FontAwesomeIcon
        icon={faTwitter}
        color={"#04AAFF"}
        size="3x"
        style={{ marginBottom: 30 }}
        className="logo"
      />
      {newAccount ? (
        <SignUpForm/>
      ):(
        <div>
          <SignInForm/>
          <div onClick={toggleAccount} className="authSpan">회원가입</div>
          <div className="socialWrap">
            <button onClick={onSocialClick} name="google">Continue with <FontAwesomeIcon icon={faGoogle} /></button>
            <button onClick={onSocialClick} name="github">Continue with <FontAwesomeIcon icon={faGithub} /></button >
          </div>
        </div>
      )
      }
      
    </div>
  );
};
export default Auth;
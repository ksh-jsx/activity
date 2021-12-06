import React, { useState } from "react";
import { authService } from "fbase";

const SignInForm = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [newAccount, setNewAccount] = useState(true);
  const [error, setError] = useState("");

  const onChange = (event) => {
    const {
      target: { name, value },
    } = event;
    if (name === "email") {
      setEmail(value);
    } else if (name === "password") {
      setPassword(value);
    }
  };
  
  const onSubmit = async (event) => {
    event.preventDefault();
    try {
      let data;
      if (newAccount) {
        data = await authService.createUserWithEmailAndPassword(
          email,
          password
        );
      } else {
        data = await authService.signInWithEmailAndPassword(email, password);
      }
      console.log(data);
    } catch (error) {
      setError(error.message);
      alert(error)
    }
  };

  //const toggleAccount = () => setNewAccount((prev) => !prev); 값 교환
  
  return (
    <div>
      <form onSubmit={onSubmit} className="signUpForm">
        <div>
          <input
            name="email"
            type="email"
            placeholder="이메일"
            required
            value={email}
            onChange={onChange}
          />
          <input
            name="password"
            type="password"
            placeholder="비밀번호"
            required
            value={password}
            onChange={onChange}
          />
        </div>
        <input
          type="submit"
          value={"회원가입"}
        />
        
      </form>
    </div>
  );
};
export default SignInForm;
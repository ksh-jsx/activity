import React, { useState } from "react";
import { authService } from "fbase";

const AuthForm = () => {
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
  const toggleAccount = () => setNewAccount((prev) => !prev);
  return (
    <div>
      <form onSubmit={onSubmit} className="authForm">
        <div>
            <input
            name="email"
            type="email"
            placeholder="Email"
            required
            value={email}
            onChange={onChange}
            />
            <input
            name="password"
            type="password"
            placeholder="Password"
            required
            value={password}
            onChange={onChange}
            />
        </div>
        <input
          type="submit"
          value={newAccount ? "계정 생성" : "로그인"}
        />
        
      </form>
      <div onClick={toggleAccount} className="authSpan">
        {newAccount ? "로그인" : "계정 생성"}
      </div>
    </div>
  );
};
export default AuthForm;
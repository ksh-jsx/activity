import React, { useState, useEffect } from "react";
import AppRouter from "components/Router";
import { authService } from "fbase";

function App() {
  const [init, setInit] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userObj, setuserObj] = useState(null);

  useEffect(() => {
    authService.onAuthStateChanged((user) => {
      if(user){
        setIsLoggedIn(true);
        setuserObj(user);
      } else {
        setIsLoggedIn(false);
      }
      setInit(true)
    });
  }, []);
  return (
    <>
      {init ?  (<AppRouter isLoggedIn={Boolean(userObj)} userObj={userObj} />) : ("Loading...")}
      <footer>&copy; Nwitter {new Date().getFullYear()}</footer>
    </>
  );
}

export default App;

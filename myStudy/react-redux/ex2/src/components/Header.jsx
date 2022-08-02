import React, { useState, useEffect } from "react";
import {useDispatch} from 'react-redux';


const Header = () =>{

  const dispatch = useDispatch();
  
  const onClick = () => {
    dispatch({type:'WELCOME'})
  }

  return (
    <header>
      <h1>
        <a href="#welcome" onClick={onClick}>WEB</a>
        </h1>
      World Wide WEB
    </header>
  );

}

export default Header;
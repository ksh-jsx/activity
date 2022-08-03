import React from "react";
import {useDispatch} from 'react-redux';

const Control = () => {

  const dispatch = useDispatch();

  const onClick = (e,mode) =>{
    e.preventDefault();
    dispatch({type:mode})
  }

  return (
    <ul>
      <li><a href="create" onClick={(e)=>onClick(e,'CREATE')}>create</a></li>
      <li><a href="update" onClick={(e)=>onClick(e,'UPDATE')}>update</a></li>
      <li>
        <input type="button" value="delete" onClick={(e)=>onClick(e,'DELETE')}/>
      </li>
    </ul>
  );
};

export default Control;
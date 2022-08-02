import React, { useState, useEffect } from "react";
import {useDispatch, useSelector} from 'react-redux';

const Nav = () =>{
  
  const dispatch = useDispatch();
  const data = useSelector((store) => store.contents);
  const tags = [];
  
  const onClick = (id) => {
    dispatch({type:'READ', id:id})
  }

  data.map((x)=>(
    tags.push(<li key={x.id}><a href="#" data-id={x.id} onClick={(e)=>onClick(e.target.dataset.id)}>{x.title}</a></li>)
  ));

  return (
    <nav>
      <ol>
        {tags}
      </ol>
    </nav>
  );

}

export default Nav;

import React, { useState} from "react";
import {useDispatch} from 'react-redux';

const Create = () => {

  const dispatch = useDispatch();
  const [values, setValues] = useState({
    title: '',
    desc:''
  })

  const onChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const onSubmit = (e) =>{
    e.preventDefault();
    dispatch({type:'CREATE_PROCESS', title:values.title, desc:values.desc })
  }

  return (
    <form onSubmit={onSubmit}>
      <p><input type="text" name="title" placeholder="title" onChange={onChange('title')} /></p>
      <p><textarea type="text" name="desc" placeholder="description" onChange={onChange('desc')} /></p>
      <p><input type="submit" /></p>
    </form>
  );
};

export default Create;
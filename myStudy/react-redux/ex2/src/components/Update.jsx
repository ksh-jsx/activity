import React, { useState} from "react";
import {useDispatch,useSelector} from 'react-redux';



const Update = () => {

  const dispatch = useDispatch();
  const contents = useSelector((store) => (
    store.contents[store.selected_content_id-1]
  ));  
  
  const [values, setValues] = useState({
    id: contents.id,
    title: contents.title,
    desc:contents.desc
  })

  const onChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const onSubmit = (e) =>{
    e.preventDefault();
    dispatch({type:'UPDATE_PROCESS', id:values.id, title:values.title, desc:values.desc })
  }

  return (
    <form onSubmit={onSubmit}>
      <input type="hidden" name="id" value={values.id}/>
      <p><input type="text" name="title" placeholder="title" onChange={onChange('title')} value={values.title}/></p>
      <p><textarea type="text" name="desc" placeholder="description" onChange={onChange('desc')} value={values.desc}/></p>
      <p><input type="submit" /></p>
    </form>
  );
};

export default Update;
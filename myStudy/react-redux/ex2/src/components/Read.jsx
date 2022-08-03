import React from "react";
import {useSelector} from 'react-redux';

const Read = () => {

  const contents = useSelector((store) => (
    store.mode === 'WELCOME' ? store.welcome_content : store.contents[store.selected_content_id-1]
  ));  

  return (
    <article>
      <h2>{contents.title}</h2>
      {contents.desc}
    </article>
  );
};

export default Read;
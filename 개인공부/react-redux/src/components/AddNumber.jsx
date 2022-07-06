import React, { useState, useEffect } from "react";

const AddNumber = ({click}) =>{

  const [size, setSize] = useState(1); 

  return (
    <div>
      <h1>Add Number</h1>
      <input type="button" value="+" onClick={()=>click(size)}/>
      <input type="text" value={size} onChange={(e)=>setSize(Number(e.target.value))}/>
    </div>
  );
}

export default AddNumber;


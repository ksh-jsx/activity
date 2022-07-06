import React, { useState, useEffect } from "react";
import store from "../store";

const DisplayNumber = ({number,unit}) =>{



  return (
    <div>
      <h1>Display Number</h1>
      <input type="text" value={number} readOnly/>{unit}
    </div>
  )
  
}

export default DisplayNumber
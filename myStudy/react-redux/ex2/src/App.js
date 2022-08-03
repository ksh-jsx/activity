import React from 'react';
import Read from './components/Read';
import Control from './components/Control';
import Header from './components/Header';
import Nav from './components/Nav';
import Create from './components/Create';
import {useDispatch, useSelector} from 'react-redux';

function App() {

  const mod = useSelector((store) => store.mode);

  let view = '';

  if(mod === 'READ' || mod === 'WELCOME')
    view =  <Read/>
  else if(mod === 'CREATE')  
    view =  <Create/>
    
  return (
    <div className='App'>
      <Header/>
      <Nav/>
      <Control/>
      {view}
      
      
    </div>
  );
}

export default App;

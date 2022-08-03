import { legacy_createStore as createStore } from "redux";

const initState = {
  mode:'WELCOME',
  welcome_content:{
    title:'WEB',
    desc:'Hello, WEB'
  },
  selected_content_id:1,
  max_content_id:3,
  contents:[
    {id:1, title:'HTML',desc:'HTML is ...'},
    {id:2, title:'CSS',desc:'CSS is ...'},
    {id:3, title:'JS',desc:'JS is ...'},
  ]
}

const reducer = (state=initState, action) => {
  if(action.type === 'WELCOME'){
    return {...state, mode:'WELCOME'};
  }
  if(action.type === 'READ'){
    return {
      ...state, 
      mode:'READ',
      selected_content_id:action.id
    };
  }
  if(action.type === 'CREATE' || action.type === 'UPDATE' || action.type === 'DELETE'){
    return {
      ...state, 
      mode:action.type,
    };
  }
  if(action.type === 'CREATE_PROCESS'){
    
    const newId = ++state.max_content_id;
    const newContents = [
      ...state.contents,
      {
        id:newId,
        title:action.title, 
        desc:action.desc
      }
    ]

    return {
      ...state, 
      contents: newContents,
      max_content_id:newId,
      mode:'READ',
      selected_content_id:newId
    };
  }
  if(action.type === 'UPDATE_PROCESS'){
    
    const newContents = [
      ...state.contents,
    ]

    newContents.filter(x=>x.id === action.id).map(x=>{
      x.title = action.title;
      x.desc = action.desc;
    })

    return {
      ...state, 
      contents: newContents,
      mode:'READ',
      selected_content_id:action.id
    };
  }
  if(action.type === 'DELETE_PROCESS'){
    
    const newContents = state.contents.filter(x=>x.id !== state.selected_content_id)
    
    console.log(newContents)
    return {
      ...state, 
      contents: newContents,
      mode:'WELCOME',
    };
  }
  return state;
}
export default createStore(reducer,window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());
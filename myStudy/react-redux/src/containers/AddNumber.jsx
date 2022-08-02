import AddNumber from "../components/AddNumber";
import {connect} from 'react-redux';
import store from "../store";

//이경우는 첫번쨰꺼 노필요

const mapDispatchToProps = (dispatch) => {
    return{
        click: (size)=>{
            dispatch({type:'INCREMENT', size:size});
        }
    }
}

export default connect(null,mapDispatchToProps)(AddNumber);


/*
import React, { Component } from "react";
import store from '../store';

export default class extends Component{
    render(){
        return <AddNumber onClick={function(size){
            store.dispatch({type:'INCREMENT', size:size});
        }.bind(this)}/>
    }
} 
*/
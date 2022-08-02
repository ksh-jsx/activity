import DisplayNumber from '../components/DisplayNumber';
import {connect} from 'react-redux';

const mapReduxStateToReactProps = (state) =>{
    return {
        number:state.number
    }
}
const mapReduxDispatchToReactProps = () => {
    return{} //지금은 필요없는데 예제용
}

export default connect(mapReduxStateToReactProps,mapReduxDispatchToReactProps)(DisplayNumber);

/*
import React, { Component } from 'react';
import store from "../store";
export default class extends Component{
    state = {number:store.getState().number}
    constructor(props){
        super(props);
        store.subscribe(function(){
            this.setState({number:store.getState().number});
        }.bind(this));
    }
    render(){
        return <DisplayNumber number={this.state.number}></DisplayNumber>
    }
} 
*/
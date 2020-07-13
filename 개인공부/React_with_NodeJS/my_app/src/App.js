import React, { Component,useState } from 'react';
import logo from './logo.svg';
import './css/App.css';
import { Link, Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import { withStyles } from '@material-ui/core/styles';
import { Redirect } from 'react-router-dom';

import { auth } from './auth/auth';
import SignIn from './auth/SignIn';
import Home from './components/Admins';
import Posts from './components/Posts';
import Journal from './components/Journal';
import Attendance from './components/Attendance';
import profile from './components/Profile';


import $ from 'jquery';
window.$ = $;


const styles = theme =>({
  
  root: {
    width: "100%",
    marginTop: 0,
    overflowX: "auto",
  },

});



class App extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      session_data:"",
      
    }
    this.callSession = this.callSession.bind(this);
  }

  callSession = async() => {
    const response = await fetch('get_session');        
    const body = await response.json();
    console.log('body data : '+body)
    return body;
  }
  
  componentDidMount(){
    this.callSession()
        .then(res => this.setState({session_data: res}))
        .catch(err => console.log(err));
  }
  
  render() {
    return (
      <Router>      
        <main>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/login" component={SignIn} />
            <Route exact path="/posts" component={Posts} />
            <Route exact path="/journal" component={Journal} />
            <Route exact path="/attendance" component={Attendance} />
            <Route exact path="/profile" component={profile} />
          </Switch>
          {this.state.session_data.logined ? (
              <Redirect to='/profile'/>
            ) : (
              <Redirect to='/login'/>
            )}
        </main>
      </Router>
      
    );
  }
}


export default withStyles(styles)(App);
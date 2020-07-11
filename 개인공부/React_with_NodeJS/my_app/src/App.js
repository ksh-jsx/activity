import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Customer from './components/Customers';
import NavigationBar from './components/NavigationBar';
import { withStyles } from '@material-ui/core/styles';



import $ from 'jquery';
window.$ = $;

function sleep(ms) { //sleep 함수
  return new Promise(resolve=>setTimeout(resolve, ms));
}

const styles = theme =>({
  
  root: {
    width: "100%",
    marginTop: 0,
    overflowX: "auto",
  },

});

class App extends Component {
  
    
  render() {
    const { classes } = this.props;
    
    return (
      <div className={classes.root}>
        <NavigationBar/>
        <Customer/>
      </div>
    );
  }
}

export default withStyles(styles)(App);
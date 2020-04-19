import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Customer from './components/Customers';

const customer = {
  'name':'김성현',
  'birthday':'981106',
  'gender':'남자',
  'job':'대학생'
}

class App extends Component{
  render(){
    return(
      <Customer
        name={customer.name}
        birthday={customer.birthday}
        gender={customer.gender}
        job={customer.job}
      />
    )
  }
}
export default App;

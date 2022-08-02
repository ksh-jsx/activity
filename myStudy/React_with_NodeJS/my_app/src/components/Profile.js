import React from 'react';
import 'date-fns';
import { post } from 'axios';
import { withStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import Navigation from './NavigationBar';
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import CreateIcon from '@material-ui/icons/Create';
import MenuBookIcon from '@material-ui/icons/MenuBook';
import RoomIcon from '@material-ui/icons/Room';
import DateFnsUtils from '@date-io/date-fns';
import Button from '@material-ui/core/Button';
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from '@material-ui/pickers';


import '../css/profile.css';

import $ from 'jquery';
window.$ = $;

const sideBarWidth = 315;
const clientWidth = $( window ).width();
const article_view = clientWidth > 600 ? `calc(100% - ${sideBarWidth}px)` : '95%'

function sleep(ms) { //sleep 함수
  return new Promise(resolve=>setTimeout(resolve, ms));
}

const styles = theme =>({
  paper: {
    marginTop: 80,
    width : article_view,
    marginLeft: '2.5%',
    marginRight: '2.5%',
    float: 'right'
  },
  progress: {
    position:'absolute',
    top:'50%',
    left:'50%',
    transform:'translate(-50%)',
    border:0
  },
  icon_wrap1: {
    position:"absolute",
    right:30,
    width:50
  },
  icon_wrap2: {
    position:"absolute",
    right:100,
    width:50,
    color:'#ffffff',
    textDecoration:'none'
  },
  txt_deco_none:{
    color:'#000000',
    textDecoration:'none'
  },
  box: {
    width:'95%',
    margin:"0 auto",
    marginTop:'20px'
  }
});

class Profile extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      admin:'',
      completed:0,
    }
    this.callApi = this.callApi.bind(this);
  }
  
  callApi = async () => {
    await sleep(1000)
    const response = await fetch('/api/profile');        
    const body = await response.json();
    return body;
  }

  componentDidMount(){
    this.timer = setInterval(this.progress, 20);
    this.callApi()
      .then(res => this.setState({admin: res}))
      .catch(err => console.log(err));
  }

  progress = () => {
    const { completed } = this.state;
    this.setState({ completed: completed >= 100 ? 0 : completed + 1 });
  };

  

  render() {
    //alert(this.state.admin)
    const filteredComponents = (data) => {
      return data.map((c) => {
        return <Data box={classes.box} key={c.id} id={c.id} image={c.image} name={c.name} birthday={c.birthday} gender={c.gender} job={c.job} />
      });
    }
    const { classes } = this.props;
    return (
      <div>
        <Navigation/>
        <Paper className={classes.paper}>
          {this.state.admin ?
            filteredComponents(this.state.admin) :
            <TableRow>
              <TableCell colSpan="6" align="center" className={classes.progress}>
                  <CircularProgress  variant="determinate" value={this.state.completed} />
              </TableCell>
            </TableRow>
          }
        </Paper>
      </div>
    )

  }
}
    
class Data extends React.Component {
  constructor(props) {            
    super(props);
    this.state = {
      title: '',  
      date : new Date(),
      content : ''
    }
    this.addJournal = this.addJournal.bind(this)
    this.handleFormSubmit = this.handleFormSubmit.bind(this)
    this.handleValueChange = this.handleValueChange.bind(this)
    this.selectedDate = this.selectedDate.bind(this)
    this.display_tab1 = this.display_tab1.bind(this)
      
  }

  addJournal(){
    const url = '/api/journal';
    const formData = new FormData();
    formData.append('title', this.state.title)
    formData.append('date', this.state.date)
    formData.append('content', this.state.content)
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    return post(url, formData, config)
  }

  handleFormSubmit(e) {
    e.preventDefault() 
    this.addJournal()
    .then((response) => {        
      window.location.reload()
    })
    this.setState({
      title: '',  
      date : new Date(),
      content : ''
    })       
       
  }
  
  handleValueChange(e) {
    let nextState = {};
    nextState[e.target.name] = e.target.value;
    this.setState(nextState);
  };

  selectedDate(e) {
    this.setState({
      date : new Date(e)
    })    
  }

  display_tab1(){
    for(var i=1;i<=3;i++)
      $('#tab'+i).hide()
      $('#tab1').show()
    
  };
  display_tab2(){
    for(var i=1;i<=3;i++)
      $('#tab'+i).hide()
    $('#tab2').show()
    
  };
  display_tab3(){
    for(var i=1;i<=3;i++)
      $('#tab'+i).hide()
    $('#tab3').show()
    
  };
/*
  moveToBottom(){
    $('html, body').animate({
      scrollTop: 350
    }, 1000);
  };
  */

  render() {  
    const { classes } = this.props;    
    return (
      <div class="data_wrap">
        <div class="top_view">
          <div class="circle_view">
            <img src={this.props.image} alt="profile"  class="profile_img"/>
          </div>
          <div class="name">{this.props.name}</div>
        </div>
        <div class="bot_view">
          <div onClick={this.display_tab1} name="tab1">
            <CreateIcon/>
            <div>개인정보 수정</div>
          </div>
          <div onClick={this.display_tab2} name="tab2">
            <MenuBookIcon/>
            <div>업무일지 관리</div>
          </div>
          <div onClick={this.display_tab3} name="tab3">
            <RoomIcon/>
            <div>출결 관리</div>
          </div>
        </div>
        <Divider />
        <div class="tabs">
          <div class="tab" id="tab1">1</div>
          <div class="tab" id="tab2">
            <TextField 
              id="outlined-basic" 
              label="Title" 
              name="title"
              value={this.state.title}
              onChange={this.handleValueChange}
              variant="outlined"
              className={this.props.box}
            />
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <KeyboardDatePicker
                margin="normal"
                id="date-picker-dialog"
                label="Date picker dialog"
                format="MM/dd/yyyy"
                name="date"
                value={this.state.date}
                onChange={this.selectedDate}
                KeyboardButtonProps={{
                  'aria-label': 'change date',
                }}
                className={this.props.box}
              />
            </MuiPickersUtilsProvider>
            <TextField
              id="outlined-multiline-static"
              label="Content"
              name="content"
              multiline
              rows={10}
              value={this.state.content}
              onChange={this.handleValueChange}
              placeholder="내용"
              variant="outlined"
              className={this.props.box}
            />
            <div className={this.props.box}>
              <Button variant="contained" color="primary" onClick={this.handleFormSubmit}>등록</Button>
            </div>
          </div>
          <div class="tab" id="tab3">3</div>
        </div>
      </div>
    )
  }
}     

export default withStyles(styles)(Profile);
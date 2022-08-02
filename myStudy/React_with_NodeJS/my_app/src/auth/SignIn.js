import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { post } from 'axios';
import { withStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import AccountCircle from '@material-ui/icons/AccountCircle';


const useStyles = theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: '#bdbdbbd',
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
});

class SignIn extends React.Component {
  constructor(props) {            
    super(props);
    this.state = {
      userId: '',            
      passwd: '',
    }
    this.handleFormSubmit = this.handleFormSubmit.bind(this)
    this.handleValueChange = this.handleValueChange.bind(this)
    this.Login = this.Login.bind(this)
  }
  
  handleFormSubmit(e) {
    e.preventDefault()
    /*
    try {
      const formData = new FormData();
      formData.append('userId', this.state.userId)
      formData.append('passwd', this.state.passwd)
      this.props.login({formData})
    } catch (e) {
      alert('Failed to login');
      this.setState({                 
        passwd: '',
      })  
    }
    */
    this.Login()
    .then((response) => {
        let user = response.data
        if(user === '비밀번호 불일치' || user === '아이디 불일치'){
          alert(user)
          this.setState({                 
            passwd: '',
          })   
        }
        else{
          window.location.reload()
        }  
        
    })
    .catch(function(err){
      alert(err)
    })
    
    this.setState({     
      userId: '',            
      passwd: '',
    })             
    
  }
  
  
  handleValueChange(e) {
    let nextState = {};
    nextState[e.target.name] = e.target.value;
    this.setState(nextState);
  }

  Login(){
    const url = '/auth/login';
    const formData = new FormData();
    formData.append('userId', this.state.userId)
    formData.append('passwd', this.state.passwd)
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }

    return post(url, formData, config)
  }

  render() {
    
    const { classes } = this.props;
    return (
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <AccountCircle/>
          </Avatar>
          <Typography component="h1" variant="h5">
            로그인
          </Typography>
          <form className={classes.form} noValidate>
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="userId"
              label="아이디"
              name="userId"
              value={this.state.userId}
              onChange={this.handleValueChange}
              autoComplete="off"
              autoFocus
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="passwd"
              label="비밀번호"
              value={this.state.passwd}
              onChange={this.handleValueChange}
              type="password"
              id="passwd"
              autoComplete="off"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
              onClick={this.handleFormSubmit}
            >
              로그인
            </Button>
          </form>
        </div>
        
      </Container>
    );
  }
}

export default withStyles(useStyles)(SignIn)
import React from 'react';
import 'date-fns';
import { post } from 'axios';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContent from '@material-ui/core/DialogContent';
import DialogActions from '@material-ui/core/DialogActions';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import DateFnsUtils from '@date-io/date-fns';
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from '@material-ui/pickers';

const styles = theme =>({
    box: {
        width:'95%',
        margin:"0 auto",
        marginTop:'20px'
    },
    btn:{
        margin:10,
        marginLeft:15,
    },
});


class JournalUpdate extends React.Component {

    constructor(props) {    
        super(props);
        this.state = {    
            open: false,
            title: this.props.title,  
            date : this.props.date,
            content : this.props.content,
        }
        this.handleFormSubmit = this.handleFormSubmit.bind(this)
        this.handleValueChange = this.handleValueChange.bind(this)
        this.selectedDate = this.selectedDate.bind(this)
        this.updateCustomer = this.updateCustomer.bind(this)
        this.handleClickOpen = this.handleClickOpen.bind(this)    
        this.handleClose = this.handleClose.bind(this);
    }

    handleFormSubmit(e) {
        e.preventDefault()
        this.updateCustomer()
        .then((response) => {
            console.log(response.data);
            this.props.stateRefresh();
        })        
    }


    handleValueChange(e) {
        let nextState = {};
        nextState[e.target.name] = e.target.value;
        this.setState(nextState);
    }

    handleClickOpen() {    
        this.setState({
            open: true    
        });
    }
    
    handleClose() {
        this.setState({
            open: false
        })
    }
    
    deleteCustomer(id){
        const url = '/api/journal/' + id;    
        fetch(url, {
            method: 'DELETE'
        });
        this.props.stateRefresh();
    }

    selectedDate(e) {
        this.setState({
            date : new Date(e)
        })    
    }
     
    updateCustomer(){
        const url = '/api/journal/' + this.props.id;    
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

    render() {
        const { classes } = this.props;
        return (
            <div>
                <Button variant="contained" color="primary" className={classes.btn} onClick={this.handleClickOpen}>
                    수정
                </Button>
                <Dialog onClose={this.handleClose} open={this.state.open}>
                    <DialogTitle onClose={this.handleClose}>
                        일지 수정
                    </DialogTitle>
                    <DialogContent>    
                        <TextField 
                            id="outlined-basic" 
                            label="Title" 
                            name="title"
                            value={this.state.title}
                            onChange={this.handleValueChange}
                            variant="outlined"
                            className={classes.box}
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
                                className={classes.box}
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
                            className={classes.box}
                        />
                        <Typography gutterBottom>
                            *수정를 클릭하면 즉시 반영됩니다.
                        </Typography>
                    </DialogContent>
                    <DialogActions>
                        <Button variant="contained" color="primary" onClick={this.handleFormSubmit}>수정</Button>
                        <Button variant="outlined" color="primary" onClick={this.handleClose}>닫기</Button>
                    </DialogActions>
                </Dialog>
            </div>
        )
    }
}
     
export default withStyles(styles)(JournalUpdate)
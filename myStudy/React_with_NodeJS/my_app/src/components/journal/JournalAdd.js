import React from 'react'
import { post } from 'axios';
import 'date-fns';
import { withStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import AddIcon from '@material-ui/icons/Add';
import DateFnsUtils from '@date-io/date-fns';
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from '@material-ui/pickers';

const styles = theme => ({
    hidden: {
        display: 'none'
    },
    formControl: {
        marginTop: theme.spacing(1),
        minWidth: 120,
      },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },  
    box: {
        width:'95%',
        margin:"0 auto",
        marginTop:'20px'
    }
});

class JournalAdd extends React.Component {
    constructor(props) {            
        super(props);
        this.state = {
            title: '',  
            date : new Date(),
            content : '',
            open:false
        }
        this.handleFormSubmit = this.handleFormSubmit.bind(this)
        this.handleValueChange = this.handleValueChange.bind(this)
        this.addJournal = this.addJournal.bind(this)
        this.selectedDate = this.selectedDate.bind(this)
        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this);
    }
    handleFormSubmit(e) {
        e.preventDefault()
        this.addJournal()
        .then((response) => {
            console.log(response.data);
            this.props.stateRefresh();
        })
        this.setState({
            title: '',  
            date : new Date(),
            content : '',
            open:false
        })            
    }

    selectedDate(e) {
        this.setState({
            date : new Date(e)
        })    
    }

    handleValueChange(e) {
        let nextState = {};
        nextState[e.target.name] = e.target.value;
        this.setState(nextState);
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

    handleClickOpen() {
        this.setState({        
            open: true
        });
    }
        
    handleClose() { 
        this.setState({
            title: '',  
            date : new Date(),
            content : '',
            open: false
        })
    }
 
    render() {
        const { classes } = this.props;
        
        return (
            <div>
                <Button variant="contained" color="primary" onClick={this.handleClickOpen}>
                    추가&nbsp;
                    <AddIcon/>
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose}>
                    <DialogTitle>일지 추가</DialogTitle>
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
                    </DialogContent>
                    <DialogActions>
                        <Button variant="contained" color="primary" onClick={this.handleFormSubmit}>추가</Button>
                        <Button variant="outlined" color="primary" onClick={this.handleClose}>닫기</Button>
                    </DialogActions>
                </Dialog>
            </div>
        )
    }
}

export default withStyles(styles)(JournalAdd)


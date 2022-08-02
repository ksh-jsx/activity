import React from 'react';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import SearchIcon from '@material-ui/icons/Search';
import InputBase from '@material-ui/core/InputBase';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import CircularProgress from '@material-ui/core/CircularProgress';
import { fade } from '@material-ui/core/styles/colorManipulator';
import Navigation from '../NavigationBar';
import JournalAdd from './JournalAdd';
import JournalUpdate from './JournalUpdate';
import JournalDelete from './JournalDelete';

import $ from 'jquery';
window.$ = $;

function sleep(ms) { //sleep 함수
    return new Promise(resolve=>setTimeout(resolve, ms));
}

const sideBarWidth = 315;
const clientWidth = $( window ).width();
const article_view = clientWidth > 600 ? `calc(100% - ${sideBarWidth}px)` : '95%'
$( window ).resize(function() {
    //clientWidth = $(window).width();
    //article_view = clientWidth > 600 ? `calc(100% - ${sideBarWidth}px)` : '95%'
});

const styles = theme =>({
    img: {
        width: 100,
    },
    menu: {
        marginTop: 80,
        marginBottom: 15,
        marginRight: 40,
        display: 'flex',
        justifyContent: 'flex-end'
      },
      paper: {
        width : article_view,
        marginLeft: '2.5%',
        marginRight: '2.5%',
        float: 'right'
      },
      table: {
        minWidth: 1080
      },
      tableRow: {
        height: 150
      },
      progress: {
        margin: theme.spacing.unit * 2
      },
      tableHead: {
        fontSize: '1.0rem',
        textAlign:'center'
      },
      tableBody: {
        textAlign:'center'
      },
      search: {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
          backgroundColor: fade(theme.palette.common.white, 0.25),
        },
        marginLeft: 0,
        width: '100%',
        [theme.breakpoints.up('sm')]: {
          marginLeft: theme.spacing.unit,
          width: 'auto',
        },
      },
      searchIcon: {
        width: theme.spacing.unit * 9,
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      },
      inputRoot: {
        color: 'inherit',
        width: '100%',
      },
      inputInput: {
        paddingTop: theme.spacing.unit,
        paddingRight: theme.spacing.unit,
        paddingBottom: theme.spacing.unit,
        paddingLeft: theme.spacing.unit * 10,
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('sm')]: {
          width: 120,
          '&:focus': {
            width: 200,
          },
        },
      }
});

class Journals extends React.Component {
    constructor(props){
        super(props);
        this.state = {
          journals:"",
          completed:0,
          searchKeyword: ''
        }
        this.stateRefresh = this.stateRefresh.bind(this);
        this.handleValueChange = this.handleValueChange.bind(this)
    }
    
    handleValueChange(e) {
        let nextState = {};
        nextState[e.target.name] = e.target.value;
        this.setState(nextState);
    }
    
    stateRefresh(){
        this.setState({
            journals:"",
            completed:0
        });

        this.callApi()
            .then(res => this.setState({journals: res}))
            .catch(err => console.log(err));
    }

    componentDidMount(){
        this.timer = setInterval(this.progress, 20);
        this.callApi()
            .then(res => this.setState({journals: res}))
            .catch(err => console.log(err));
    }

    componentWillUnmount() {
        clearInterval(this.timer);
    }
    
    callApi = async () => {
      await sleep(500)
      const response = await fetch('/api/journal');        
      const body = await response.json();
      console.log(body)
      return body;
    }

    progress = () => {
        const { completed } = this.state;
        this.setState({ completed: completed >= 100 ? 0 : completed + 1 });
    };

    render() {
        const { classes } = this.props;
        const cellList = ["제목", "날짜", "설정"]
        const filteredComponents = (data) => {
          data = data.filter((c) => {
            return c.title.indexOf(this.state.searchKeyword) > -1;
          });
          return data.reverse().map((c) => {
            return <JournalList stateRefresh={this.stateRefresh} tableRow= {classes.tableRow} tableBody= {classes.tableBody} key={c.id} id={c.id} title={c.title} content={c.content} date={c.createdDate} />
          });
        }
        return (
            <div>
              <Navigation current_link="/journal"/>
              <div className={classes.menu}>
                <div className={classes.search}>
                    <div className={classes.searchIcon}>
                    <SearchIcon />
                    </div>
                    <InputBase
                    placeholder="제목으로 검색"
                    classes={{
                        root: classes.inputRoot,
                        input: classes.inputInput,
                    }}
                    autoComplete="off"
                    name="searchKeyword"
                    value={this.state.searchKeyword}
                    onChange={this.handleValueChange}
                    />
                </div>
                <JournalAdd stateRefresh={this.stateRefresh} />
              </div>
                <Paper className={classes.paper}>
                    <Table>
                        <TableHead>
                        <TableRow>
                            {cellList.map(c => {
                            return <TableCell className={classes.tableHead}>{c}</TableCell>
                            })}
                        </TableRow>
                        </TableHead>
                        <TableBody>
                        {this.state.journals ?
                            filteredComponents(this.state.journals) :
                            <TableRow>
                            <TableCell colSpan="6" align="center">
                                <CircularProgress className={classes.progress} variant="determinate" value={this.state.completed} />
                            </TableCell>
                            </TableRow>
                        }
                        </TableBody>
                    </Table>
                </Paper>
            </div>
        
        )

    }
}
    
class JournalList extends React.Component {
    render() {
        const { classes } = this.props;
        return (
            <TableRow className={this.props.tableRow}>
                <TableCell className={this.props.tableBody}>{this.props.title}</TableCell>
                <TableCell className={this.props.tableBody}>{this.props.date}</TableCell>
                <TableCell className={this.props.tableBody}>
                  <JournalUpdate stateRefresh={this.props.stateRefresh} id={this.props.id} title={this.props.title} content={this.props.content} date={this.props.date}/>
                  <JournalDelete stateRefresh={this.props.stateRefresh} id={this.props.id}/>
                </TableCell>
            </TableRow>
        )
    }
}
      

export default withStyles(styles)(Journals);
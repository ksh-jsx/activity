import React from 'react';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import CustomerDelete from './CustomerDelete'
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import CustomerAdd from './CustomerAdd';
import SearchIcon from '@material-ui/icons/Search';
import InputBase from '@material-ui/core/InputBase';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import CircularProgress from '@material-ui/core/CircularProgress';
import { fade } from '@material-ui/core/styles/colorManipulator';
import Navigation from './NavigationBar';

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
        fontSize: '1.0rem'
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

class Admins extends React.Component {
    constructor(props){
        super(props);
        this.state = {
          customers:"",
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
            customers:"",
            completed:0
        });

        this.callApi()
            .then(res => this.setState({customers: res}))
            .catch(err => console.log(err));
    }

    componentDidMount(){
        this.timer = setInterval(this.progress, 20);
        this.callApi()
            .then(res => this.setState({customers: res}))
            .catch(err => console.log(err));
    }

    componentWillUnmount() {
        clearInterval(this.timer);
    }
    
    callApi = async () => {
      await sleep(500)
      const response = await fetch('/api/admins');        
      const body = await response.json();
      return body;
    }

    progress = () => {
        const { completed } = this.state;
        this.setState({ completed: completed >= 100 ? 0 : completed + 1 });
    };

    render() {
        const { classes } = this.props;
        const cellList = ["번호", "프로필 이미지", "이름", "생년월일", "성별", "직업", "설정"]
        const filteredComponents = (data) => {
          data = data.filter((c) => {
            return c.name.indexOf(this.state.searchKeyword) > -1;
          });
          return data.map((c) => {
            return <AdminList stateRefresh={this.stateRefresh} imgCss = {classes.img} rowCss= {classes.tableRow} key={c.id} id={c.id} image={c.image} name={c.name} birthday={c.birthday} gender={c.gender} job={c.job} />
          });
        }
        return (
            <div>
              <Navigation current_link="/"/>
              <div className={classes.menu}>
                <div className={classes.search}>
                    <div className={classes.searchIcon}>
                    <SearchIcon />
                    </div>
                    <InputBase
                    placeholder="검색하기"
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
                <CustomerAdd stateRefresh={this.stateRefresh} />
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
                        {this.state.customers ?
                            filteredComponents(this.state.customers) :
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
    
class AdminList extends React.Component {
    render() {
        const { classes } = this.props;
        return (
            <TableRow className={this.props.rowCss}>
                <TableCell>{this.props.id}</TableCell>
                <TableCell><img src={this.props.image} alt="profile"  className={this.props.imgCss}/></TableCell>
                <TableCell>{this.props.name}</TableCell>
                <TableCell>{this.props.birthday}</TableCell>
                <TableCell>{this.props.gender}</TableCell>
                <TableCell>{this.props.job}</TableCell>
                <TableCell><CustomerDelete stateRefresh={this.props.stateRefresh} id={this.props.id}/></TableCell>
            </TableRow>
        )
    }
}
      

export default withStyles(styles)(Admins);
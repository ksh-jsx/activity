import React from 'react';
import PropTypes from 'prop-types';
import { Route, Redirect,Link } from 'react-router-dom';
import AppBar from '@material-ui/core/AppBar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Divider from '@material-ui/core/Divider';
import Drawer from '@material-ui/core/Drawer';
import Hidden from '@material-ui/core/Hidden';
import IconButton from '@material-ui/core/IconButton';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import PeopleIcon from '@material-ui/icons/People';
import CreateIcon from '@material-ui/icons/Create';
import MenuIcon from '@material-ui/icons/Menu';
import LibraryBooksOutlinedIcon from '@material-ui/icons/LibraryBooksOutlined';
import LocationOnOutlinedIcon from '@material-ui/icons/LocationOnOutlined';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import AccountCircle from '@material-ui/icons/AccountCircle';

import $ from 'jquery';
window.$ = $;

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    zIndex:9999
  },
  drawer: {
    [theme.breakpoints.up('sm')]: {
      width: drawerWidth,
      flexShrink: 0,
    },
  },
  appBar: {
    [theme.breakpoints.up('sm')]: {
      width: `100%`,
      marginLeft: drawerWidth,
      zIndex:9999
    },
  },
  menuButton: {
    marginRight: theme.spacing(2),
    [theme.breakpoints.up('sm')]: {
      display: 'none',
    },
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
  drawerPaper: {
    width: drawerWidth,
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
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
  clicked_item:{    
    textDecoration:'none',
    backgroundColor:'#bdbdbd',
    color:'#ffffff'
  },
  unclicked_item:{    
    textDecoration:'none',
    backgroundColor:'#ffffff',
    color:'#000000'
  }
}));

function NavigationBar(props) {
  const { window } = props;
  const classes = useStyles();
  const theme = useTheme();
  const [mobileOpen, setMobileOpen] = React.useState(false);
  
  //console.log(props.current_link)
  

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const handleLogout = () => {
    fetch('/logout'); 
  };
  const drawer = (
    <div>
      <div className={classes.toolbar} />
      <Divider />
        <List>
          <Link to="/" className={classes.txt_deco_none}>
            <ListItem button key='직원 관리' className={props.current_link == '/' ? classes.clicked_item : classes.unclicked_item }>
              <ListItemIcon>
                  <PeopleIcon />
                </ListItemIcon>
              <ListItemText primary='직원 관리' />
            </ListItem>
          </Link>

          <Link to="/posts" className={classes.txt_deco_none}>
            <ListItem button key='글 관리' className={props.current_link == '/posts' ? classes.clicked_item : classes.unclicked_item }>
              <ListItemIcon>
                  <LibraryBooksOutlinedIcon />
                </ListItemIcon>
              <ListItemText primary='글 관리' />
            </ListItem>
          </Link>
        </List>
      <Divider />
      <List>
        <Link to="/journal" className={classes.txt_deco_none}>
          <ListItem button key='업무일지 관리' className={props.current_link == '/journal' ? classes.clicked_item : classes.unclicked_item }>
            <ListItemIcon>
                <PeopleIcon />
              </ListItemIcon>
            <ListItemText primary='업무일지 관리' />
          </ListItem>
        </Link>

        <Link to="/attendance" className={classes.txt_deco_none}>
          <ListItem button key='출결 관리' className={props.current_link == '/attendence' ? classes.clicked_item : classes.unclicked_item }>
            <ListItemIcon>
                <LibraryBooksOutlinedIcon />
              </ListItemIcon>
            <ListItemText primary='출결 관리' />
          </ListItem>
        </Link>
      </List>
    </div>
  );

  const container = window !== undefined ? () => window().document.body : undefined;

  return (
    <div className={classes.root}>
    <CssBaseline />
    <AppBar position="fixed" className={classes.appBar}>
      <Toolbar>
        <IconButton
          color="inherit"
          aria-label="open drawer"
          edge="start"
          onClick={handleDrawerToggle}
          className={classes.menuButton}
        >
            <MenuIcon />
        </IconButton>
        <Typography variant="h6" noWrap>
          직원 관리 시스템
        </Typography>
        <IconButton aria-label="account of current user" aria-controls="primary-search-account-menu" aria-haspopup="true" color="inherit" className={classes.icon_wrap1}>
          <Link to="/profile" className={classes.txt_deco_none}>
            <AccountCircle  />
          </Link>
        </IconButton>
        <Link to="/login" className={classes.icon_wrap2}>
          <Button color="inherit"  onClick={handleLogout}>LOGOUT</Button>
        </Link>
      </Toolbar>
    </AppBar>
    <nav className={classes.drawer} aria-label="mailbox folders">
      {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
      <Hidden smUp implementation="css">
        <Drawer
          container={container}
          variant="temporary"
          anchor={theme.direction === 'rtl' ? 'right' : 'left'}
          open={mobileOpen}
          onClose={handleDrawerToggle}
          classes={{
            paper: classes.drawerPaper,
          }}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
        >
          {drawer}
        </Drawer>
      </Hidden>
      <Hidden xsDown implementation="css">
        <Drawer
          classes={{
            paper: classes.drawerPaper,
          }}
          variant="permanent"
          open
        >
          {drawer}
        </Drawer>
      </Hidden>
    </nav>
    </div>
    );
}
export default NavigationBar;

import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import $ from 'jquery';
window.$ = $;

function sleep(ms) { //sleep 함수
  return new Promise(resolve=>setTimeout(resolve, ms));
}

const styles = theme =>({
    
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
        return <AdminList key={c.id} id={c.id} image={c.image} name={c.name} birthday={c.birthday} gender={c.gender} job={c.job} />
      });
    }

    return (
      <div>
        <dd>
        {this.state.admin ?
          filteredComponents(this.state.admin) :
          <TableRow>
            <TableCell colSpan="6" align="center">
                <CircularProgress  variant="determinate" value={this.state.completed} />
            </TableCell>
          </TableRow>
        }
        </dd>
      </div>
    )

  }
}
    
class AdminList extends React.Component {
  render() {
      const { classes } = this.props;
      return (
        <div >
            <div>{this.props.id}</div>
            <div><img src={this.props.image} alt="profile"  /></div>
            <div>{this.props.name}</div>
            <div>{this.props.birthday}</div>
            <div>{this.props.gender}</div>
            <div>{this.props.job}</div>
            
        </div>
      )
  }
}     

export default withStyles(styles)(Profile);
import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import Navigation from './NavigationBar';

import $ from 'jquery';
window.$ = $;


const styles = theme =>({
    
});

class Posts extends React.Component {

    
    
    handleValueChange(e) {

    }
    
    stateRefresh(){
    }

    componentDidMount(){

    }

    componentWillUnmount() {

    }
    
    callApi = async () => {

    }

    progress = () => {
        
    };

    render() {
        
        return (
            <div>
              <Navigation current_link="/posts"/>
            
            </div>
        
        )

    }
}
    
      

export default withStyles(styles)(Posts);
import React, { Component } from 'react';
import * as $ from 'jquery'
import './css/App.css';
import Movie from './Movie';
import Loading from './Loading';

window.jQuery = window.$ = $

class App extends Component {

  // 랜더 : componentWillMount() -> render() -> componentDidMount()
  // Update : componentWillReceiveProps() -> shouldComponentUpdate() -> componentWillUpdate() -> render() -> componentDidUpdate()
  count = 10;
  state = {}
  
  componentDidMount(){
    this._getMovies();
    window.addEventListener('scroll',this._infiniteScroll, true)
  }

  _getMovies = async function(){
    const movies = await this._callApi()
    console.log(movies)
    this.setState({
      movies
    })
  }

  _callApi = () => {
    return fetch('https://yts.lt/api/v2/list_movies.json?sort_by=like_count&limit=10')
    .then(res => res.json())
    .then(json => json.data.movies)
    .catch(err => console.log(err))
  }

  _callApi_whenScroll = () => {
    return fetch('https://yts.lt/api/v2/list_movies.json?sort_by=like_count&limit=50')
    .then(res => res.json())
    .then(json => json.data.movies)
    .catch(err => console.log(err))
  }
  _renderMovies = () => { //영화정보 movie.js로 랜더링
    const movies = this.state.movies.map((movie) => {
      return <Movie title={movie.title} poster={movie.large_cover_image} key={movie.id}/>
    })
    return movies
  }
  
  _loading = () => { //로딩화면
    return <Loading/>
  }
  
  _infiniteScroll = async () => { //인피니트 스크롤 
    const loaded_movies = await this._callApi_whenScroll()
    //스크롤 위치 = Math.floor($(document).scrollTop());
    //페이지의 세로길이 = $(document).height();
    //모니터의 세로길이 = $(window).height();

    if(Math.round( Math.floor($(window).scrollTop())) >=  ($(document).height() - $(window).height())/1.2){
      if(loaded_movies.length >= this.count+2){
        this.setState({
          movies :
          [
            ...this.state.movies,
            {
              id : loaded_movies[this.count].id,
              title : loaded_movies[this.count].title,
              large_cover_image : loaded_movies[this.count++].large_cover_image

            },
            {
              id : loaded_movies[this.count].id,
              title : loaded_movies[this.count].title,
              large_cover_image : loaded_movies[this.count++].large_cover_image
            }
          ]
        })
      }
    }
  }

  render(){
    return (
      <div className="App">
        {this.state.movies ? this._renderMovies() : this._loading()}
      </div>
    );
  }
}

export default App;
import React, { Component } from  'react';
import PropTypes from 'prop-types'
import './css/Movie.css';

function Movie({title, poster})
{
    return (
            <div className="movie_info">
                <MoviePoster poster ={poster} />
                <h1>{title}</h1>
            </div>
    )
}

function MoviePoster({poster}){
    return (
        <img src={poster} alt="Movie poster"/>
    )
}

Movie.propTypes = {
    title: PropTypes.string.isRequired,
    poster : PropTypes.string.isRequired
}

MoviePoster.propTypes = {
    poster : PropTypes.string.isRequired
}

export default Movie;
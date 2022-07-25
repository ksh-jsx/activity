import 'package:flutter/material.dart';
import 'package:flutter_ex/model/model_movie.dart';
import 'package:flutter_ex/screen/detail_screen.dart';

class CircleSlider extends StatelessWidget{
  final List<Movie>? movies;
  CircleSlider({Key? key, this.movies}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(7),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('미리보기', style: TextStyle(color: Colors.white),),
          Container(
            margin: EdgeInsets.only(top: 10),
            height: 120,
            child: ListView(
              scrollDirection: Axis.horizontal,
              children: makeCircleImages(context, movies!),
            ),
          )
        ],
      ),
    );
  }
}

List<Widget> makeCircleImages(BuildContext context, List<Movie> movies) {
  List<Widget> results = [];
  for (var i=0 ; i<movies.length ; i++) {
    results.add(
      InkWell(
        onTap: () {
          Navigator.of(context).push(MaterialPageRoute<Null>(
              fullscreenDialog: true,
              builder: (BuildContext context) {
                return DetailScreen(
                  movie: movies[i],
                );
              }
          ));
        },
        child: Container(
          padding: EdgeInsets.only(right: 10),
          child: Align(
            alignment: Alignment.centerLeft,
            child:CircleAvatar(
              backgroundImage: AssetImage(movies[i].poster),
              radius:45
            ),
          ),
        ),
      )
    );
  }

  return results;
}
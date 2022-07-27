
import 'package:flutter/material.dart';
import 'package:tdl/screen/home_screen.dart';
import 'package:tdl/widget/bottom_bar.dart';

void main() async {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp>{
  //TabController controller;
  @override
  Widget build(BuildContext context) {
    return MaterialApp(

      title: 'TodoList',
      theme: ThemeData(
        brightness: Brightness.dark,
        primaryColor: Colors.black,
        accentColor: Colors.white,
      ),
      home: DefaultTabController(
        length: 4,
        child: Scaffold(
          body:TabBarView(
            physics: NeverScrollableScrollPhysics(),
            children: <Widget>[
              HomeScreen(),
              Container( child: Text('2'),),
              Container( child: Text('3'),),
              Container( child: Text('4'),),
            ],
          ),
          bottomNavigationBar: Bottom(),
        ),
      ),
    );
  }
}

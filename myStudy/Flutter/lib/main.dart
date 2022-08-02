import 'package:firebase_core/firebase_core.dart';
import 'package:flutter_ex/screen/add_screen.dart';
import 'package:flutter_ex/screen/like_screnn.dart';
import 'package:flutter_ex/screen/more_screnn.dart';
import 'package:flutter_ex/screen/search_screnn.dart';
import 'firebase_options.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ex/screen/home_screen.dart';
import 'package:flutter_ex/widget/bottom_bar.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
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

      title: 'NetFilx',
      theme: ThemeData(
        brightness: Brightness.dark,
        primaryColor: Colors.black,
        accentColor: Colors.white,
      ),
      home: DefaultTabController(
        length: 5,
        child: Scaffold(
          body:TabBarView(
            physics: NeverScrollableScrollPhysics(),
            children: <Widget>[
              HomeScreen(),
              SearchScreen(),
              LikeScreen(),
              AddScreen(),
              MoreScreen(),
            ],
          ),
          bottomNavigationBar: Bottom(),
        ),
      ),
    );
  }
}

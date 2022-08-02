import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter_linkify/flutter_linkify.dart';
import 'package:url_launcher/url_launcher.dart';

class MoreScreen extends StatefulWidget {
  _MoreScreenState createState() => _MoreScreenState();
}

class _MoreScreenState extends State<MoreScreen> {
  int count = 0;
  List<Widget> results = [];
  void initState(){
    super.initState();
  }

  List<Widget> makeObject(BuildContext context) {
    List<Widget> tmp = [];
    
    return tmp+results;
  }

  @override
  Widget build(BuildContext context){
    return Container(
      child: Column(
        children: [
          Container(
            padding: EdgeInsets.fromLTRB(0,50,0,30),
            child:CircleAvatar(
                backgroundImage: AssetImage('bbongflix_logo.png'),
                radius:100
            ),
          ),
          Text(
            '김성현',
            style: TextStyle(
              decoration: TextDecoration.underline,
              decorationColor: Color(0xffC1463C),
                decorationThickness: 4,
              fontSize: 20
            ),
          ),
          Container(
            padding: EdgeInsets.only(top:5),
            child: Linkify(onOpen: (link) async {
              if(await canLaunch(link.url)) {
                await launch(link.url);
              }
            },
              text: "https://github.com/shkim787",
              linkStyle: TextStyle(color: Color(0xffffffff)),
            ),
          ),
          Container(
            padding: EdgeInsets.fromLTRB(10,20,10,10),
            child: TextButton(
              child: Container(
                color: Color(0xffff0000),
                height: 30,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(Icons.edit, color: Colors.white,),
                    SizedBox(width: 10,),
                    Text(
                      '프로필 수정하기',
                      style:TextStyle(color: Colors.white)
                    )
                  ],
                ),
              ),
              onPressed: () {
                setState(() {
                  count++;
                  results.add(
                      Container(
                        color: Colors.amber,
                        child: Text(count.toString()),
                      )
                  );
                  log(count.toString());
                });
              },
            ),
          ),
          Container(
            height: 200,
            color: Colors.black12,
            child: ListView(
              children: makeObject(context),
            ),
          ),
        ],
      ),
    );
  }
}
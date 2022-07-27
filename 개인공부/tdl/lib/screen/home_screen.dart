import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final TextEditingController _title = TextEditingController();
  FocusNode focusNode = FocusNode();
  String _titleText = "";

  @override
  void initState() {
    super.initState();
  }

  _HomeScreenState() {

  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child:Row(
        children: [
          TextField(
            focusNode: focusNode,
            decoration: InputDecoration(
              filled: true,
              fillColor: Colors.white12,
            ),
          ),
          TextField(
            focusNode: focusNode,
            decoration: InputDecoration(
              filled: true,
              fillColor: Colors.white12,
            ),
          ),
        ],
      ),

    );
  }

}
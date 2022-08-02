import 'dart:developer';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class AddScreen extends StatefulWidget {
  const AddScreen({Key? key}) : super(key: key);
  _AddScreenState createState() => _AddScreenState();
}

class _AddScreenState extends State<AddScreen> {

  final formKey = GlobalKey<FormState>();
  String title = '';
  String keyword = '';
  String poster = '';

  @override
  void initState(){
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Form(
      key: formKey,
      child: Column(
        children: [
          renderTextFormField(
            label: '영화 제목',
            onSaved: (val) {
              setState(() {
                title = val;
              });
            },
            validator: (val) {
              if(val.length < 1) {
                return '제목은 필수사항입니다.';
              }
            },
          ),
          renderTextFormField(
            label: '영화 설명',
            onSaved: (val) {
              setState(() {
                keyword = val;
              });
            },
            validator: (val) {
              if(val.length < 1) {
                return '설명은 필수사항입니다.';
              }
            },
          ),
          renderTextFormField(
            label: '포스터 링크',
            onSaved: (val) {
              setState(() {
                poster = val;
              });
            },
            validator: (val) {
              if(val.length < 1) {
                return '링크은 필수사항입니다.';
              }
            },
          ),
          Container(
            width: double.infinity,
            color: Colors.blueAccent,
            margin: EdgeInsets.only(top:30),
            child: TextButton(
              onPressed: () async{
                if(formKey.currentState!.validate()){
                  formKey.currentState?.save();
                  final doc = FirebaseFirestore.instance.collection('movie').doc();
                  await doc.set({'id':doc.id,'title':title,'keyword':keyword,'poster':poster,'like':false});
                  Get.snackbar(
                    '저장완료!',
                    '폼 저장이 완료되었습니다!',
                    backgroundColor: Colors.white,
                  );
                }
              },
              child: Center(
                child: Text(
                  '추가',
                  style: TextStyle(
                      fontSize: 20,
                      color: Colors.white
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  renderTextFormField({
    required String label,
    required FormFieldSetter onSaved,
    required FormFieldValidator validator,
  }) {
    return Container(
      padding: EdgeInsets.all(5),
      child: TextFormField(
        onSaved: onSaved,
        validator: validator,
        decoration: InputDecoration(
          hintText: label,
          filled: true,
          fillColor: Colors.white12,
          labelStyle: TextStyle(color: Colors.white),
          focusedBorder: OutlineInputBorder(
              borderSide: BorderSide(color: Colors.transparent),
              borderRadius: BorderRadius.all(Radius.circular(10))
          ),
          enabledBorder: OutlineInputBorder(
              borderSide: BorderSide(color: Colors.transparent),
              borderRadius: BorderRadius.all(Radius.circular(10))
          ),
          border: OutlineInputBorder(
              borderSide: BorderSide(color: Colors.transparent),
              borderRadius: BorderRadius.all(Radius.circular(10))
          ),
        ),
      ),
    );
  }

}
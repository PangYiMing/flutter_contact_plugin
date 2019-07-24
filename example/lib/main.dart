import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_contact_plugin/flutter_contact_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _contactInfo = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    String contactInfo;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await FlutterContactPlugin.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }
    try {
      contactInfo = await FlutterContactPlugin.contactInfo;
    } on PlatformException {
      contactInfo = 'Failed to get contact info.';
    }
    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
      _contactInfo = contactInfo;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: Container(
              child: Column(
                children: <Widget>[
                  Text('Running on: $_platformVersion\n'),
                  Text('contact info: $_contactInfo\n'),
                ],
              ),
              height: 300,
              color: Color.fromRGBO(1, 254, 1, 0.5),
        )),
      ),
    );
  }
}

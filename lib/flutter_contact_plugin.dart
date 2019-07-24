import 'dart:async';

import 'package:flutter/services.dart';

class FlutterContactPlugin {
  static const MethodChannel _channel =
      const MethodChannel('flutter_contact_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get contactInfo async {
    final String version = await _channel.invokeMethod('getContactInfo');
    return version;
  }
}

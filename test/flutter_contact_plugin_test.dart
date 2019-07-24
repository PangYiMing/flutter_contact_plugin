import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_contact_plugin/flutter_contact_plugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_contact_plugin');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FlutterContactPlugin.platformVersion, '42');
  });
  test('getContactInfo', () async {
    expect(await FlutterContactPlugin.contactInfo, '42');
  });
}

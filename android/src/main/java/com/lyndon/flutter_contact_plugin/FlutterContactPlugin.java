package com.lyndon.flutter_contact_plugin;

import io.flutter.app.FlutterApplication;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FlutterContactPlugin
 */
public class FlutterContactPlugin implements MethodCallHandler {
  String TAG = "FlutterContactPlugin";
  Activity activity;

  public FlutterContactPlugin(Activity activity) {
    this.activity = activity;
  }

  /**
   * Plugin registration.
   */
  public static void registerWith(Registrar registrar, Activity activity) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_contact_plugin");
    channel.setMethodCallHandler(new FlutterContactPlugin(activity));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("getContactInfo")) {
     String info = queryContactPhoneNumber();
      result.success("Android " + info);
    } else {
      result.notImplemented();
    }
  }

  private String queryContactPhoneNumber() {
    String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
    Cursor cursor = this.activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols, null, null, null);
    Log.d("cursor.getCount()", "ss");
    List<Object> list = new ArrayList<Object>();
    for (int i = 0; i < cursor.getCount(); i++) {
      cursor.moveToPosition(i);
      // 取得联系人名字
      int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
      int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
      String name = cursor.getString(nameFieldColumnIndex);
      String number = cursor.getString(numberFieldColumnIndex);
      try {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("number", number);
        list.add(json);
      } catch (JSONException e) {
        Log.e(TAG, e.toString());
      }

    }
    return list.toString();
  }

}

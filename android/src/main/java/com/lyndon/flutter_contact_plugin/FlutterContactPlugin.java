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

//
//
//  private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
//  private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
//  private static final int MY_PERMISSIONS_REQUEST_INTERNET = 2;
//
//  public static void requestPermissions(Activity thisActivity) {
//    requestPermission(thisActivity, Manifest.permission.READ_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
//    requestPermission(thisActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//    requestPermission(thisActivity, Manifest.permission.INTERNET, MY_PERMISSIONS_REQUEST_INTERNET);
//  }
//
//  static void requestPermission(Activity thisActivity, String permission, int code) {
//    if (ContextCompat.checkSelfPermission(thisActivity, permission)
//            != PackageManager.PERMISSION_GRANTED) {
//      if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity, permission)) {
//        // Show an expanation to the user *asynchronously* -- don't block
//        // this thread waiting for the user's response! After the user
//        // sees the explanation, try again to request the permission.
//
//      } else {
//
//        // No explanation needed, we can request the permission.
//
//        ActivityCompat.requestPermissions(thisActivity,
//                new String[]{permission},
//                code);
//        //  MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
//        // app-defined int constant. The callback method gets the
//        // result of the request.
//      }
//    }
//  }
//
//  @Override
//  public void onRequestPermissionsResult(int requestCode,
//                                         String permissions[], int[] grantResults) {
//    switch (requestCode) {
//      case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
//        // If request is cancelled, the result arrays are empty.
//        if (grantResults.length > 0
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//          // permission was granted, yay! Do the
//          // contacts-related task you need to do.
//        } else {
//          onDestroy();
//          // permission denied, boo! Disable the
//          // functionality that depends on this permission.
//        }
//        return;
//      }
//      case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
//        // If request is cancelled, the result arrays are empty.
//        if (grantResults.length > 0
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//          // permission was granted, yay! Do the
//          // contacts-related task you need to do.
//        } else {
//          onDestroy();
//          // permission denied, boo! Disable the
//          // functionality that depends on this permission.
//        }
//        return;
//      }
//
//      case MY_PERMISSIONS_REQUEST_INTERNET: {
//        // If request is cancelled, the result arrays are empty.
//        if (grantResults.length > 0
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//          // permission was granted, yay! Do the
//          // contacts-related task you need to do.
//
//        } else {
//          // permission denied, boo! Disable the
//          // functionality that depends on this permission.
//        }
//        return;
//      }
//      // other 'case' lines to check for other
//      // permissions this app might request
//    }
//  }

}

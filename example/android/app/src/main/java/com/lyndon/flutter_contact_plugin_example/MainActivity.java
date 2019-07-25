package com.lyndon.flutter_contact_plugin_example;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this,this);
    requestPermissions(this);
  }




  private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
  private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
  private static final int MY_PERMISSIONS_REQUEST_INTERNET = 2;
  private static final int MY_PERMISSIONS_READ_CONTACTS = 3;

  public static void requestPermissions(Activity thisActivity) {
//    requestPermission(thisActivity, Manifest.permission.READ_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
//    requestPermission(thisActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//    requestPermission(thisActivity, Manifest.permission.INTERNET, MY_PERMISSIONS_REQUEST_INTERNET);
    requestPermission(thisActivity, Manifest.permission.READ_CONTACTS, MY_PERMISSIONS_READ_CONTACTS);
  }

  static void requestPermission(Activity thisActivity, String permission, int code) {
    if (ContextCompat.checkSelfPermission(thisActivity, permission)
            != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity, permission)) {
        // Show an expanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.

      } else {

        // No explanation needed, we can request the permission.

        ActivityCompat.requestPermissions(thisActivity,
                new String[]{permission},
                code);
        //  MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
        // app-defined int constant. The callback method gets the
        // result of the request.
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {
    switch (requestCode) {
      case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          // permission was granted, yay! Do the
          // contacts-related task you need to do.
        } else {
          onDestroy();
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }
      case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          // permission was granted, yay! Do the
          // contacts-related task you need to do.
        } else {
          onDestroy();
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }

      case MY_PERMISSIONS_REQUEST_INTERNET: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          // permission was granted, yay! Do the
          // contacts-related task you need to do.

        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }

      case MY_PERMISSIONS_READ_CONTACTS: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          // permission was granted, yay! Do the
          // contacts-related task you need to do.

        } else {
          onDestroy();
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }
      // other 'case' lines to check for other
      // permissions this app might request
    }
  }

}

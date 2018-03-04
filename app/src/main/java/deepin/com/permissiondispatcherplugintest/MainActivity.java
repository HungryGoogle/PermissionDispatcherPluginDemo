package deepin.com.permissiondispatcherplugintest;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends Activity {
    public static final String TAG = "leeTest------>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.id_auto_request_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "call requestCameraPermission 1 ");
                MainActivityPermissionsDispatcher.requestPermissionWithCheck(MainActivity.this);
                Log.i(TAG, "call requestCameraPermission 2 ");
            }
        });
    }

    @NeedsPermission({Manifest.permission.CAMERA})
    void requestCameraPermission() {
        Log.i(TAG, "requestCameraPermission succeed... ");
        Toast.makeText(this,"开启摄像头权限成功", Toast.LENGTH_LONG).show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void cameraPermissionDenied() {
        Log.i(TAG, "deniedCameraPermission ... ");
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void cameraPermissionNerverAskAgain() {
        Log.i(TAG, "onCameraNeverAskAgain ... ");
        new AlertDialog.Builder(this)
                .setPositiveButton("朕亲去开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("需要摄像头权限，陛下您已经设置拒接开启，并勾选了不再提醒")
                .show();

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.i(TAG, "onRequestPermissionsResult requestCod = " + requestCode + ", grantResults = " + grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


//
//
//    @OnShowRationale(Manifest.intent能跳转到的界面.CAMERA)
//    void cameraShowRationale(final PermissionRequest request) {
//        new AlertDialog.Builder(this)
//                .setPositiveButton("恩准", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 继续申请
//                        request.proceed();
//                    }
//                })
//                .setNegativeButton("拒接", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.cancel();
//                    }
//                })
//                .setCancelable(false)
//                .setMessage("需要摄像头权限，请陛下恩准")
//                .show();
//
//    }
}

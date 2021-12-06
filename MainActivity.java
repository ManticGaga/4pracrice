package it.mirea.kazakov;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Build;

import android.os.Build;
import android.Manifest;
import android.content.pm.PackageManager;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 112;

    private static final int NOTIFY_ID = 23;
    private static final String NOTIFICATION_CHANNEL_ID = "Channel";

    Button btnCamera;
    Button btnNotify;

    SurfaceView sv;

    SurfaceHolder holder;



    final boolean FULL_SCREEN = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = findViewById(R.id.btnCamera);
        btnNotify = findViewById(R.id.notify_btn);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification","My Notification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"My Notification");
                builder.setContentTitle("Практическая работа 4");
                builder.setContentText("Казаков Юрий Михайлович ИКБО-25-20");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());

            }
        });




        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);


            }
        });
    }






    public void checkPermission(String permission, int requestCode) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            //take permissions
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        } else {

            Toast.makeText(MainActivity.this, "Permission", Toast.LENGTH_LONG).show();


        }
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnCamera.setText("Permission Granted");
                Camera camera = Camera.open();
                SurfaceView surfaceView = findViewById(R.id.surfaceView);
                SurfaceHolder surfaceHolder = surfaceView.getHolder();
                try {
                    camera.setPreviewDisplay(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();

            } else {
                Toast.makeText(MainActivity.this, "Camera permission Denied", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
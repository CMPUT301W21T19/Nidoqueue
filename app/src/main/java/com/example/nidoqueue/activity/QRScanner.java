package com.example.nidoqueue.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.QRCodeFoundListener;
import com.example.nidoqueue.model.QRCodeImageAnalyzer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.firestore.FirebaseFirestore;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Classname:   QRScanner
 * Sources:     based off of tutorial by learntodroid
 *              How to Create a QR Code Scanner App in Android
 *              https://learntodroid.com/how-to-create-a-qr-code-scanner-app-in-android/
 * Version:     final
 * Date:        April 9th, 2021
 * Purpose:     QR code scanning activity
 * Issues:      None
 */
public class QRScanner extends AbstractActivity {
    //region QR Scanning variables
    private static final int PERMISSION_REQUEST_CAMERA = 0;

    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    private Button qrCodeFoundButton;
    private String qrCode;
    //endregion
    private ImageButton btnBack, btnHome;

    RequestManager requestManager = RequestManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scaner);

        btnBack = findViewById(R.id.back_buttonScanner);
        btnHome = findViewById(R.id.home_buttonScanner);

        btnBack.setOnClickListener(goBack);
        btnHome.setOnClickListener(goHome);

        //region QR Scanning functionality
        previewView = findViewById(R.id.previewView);

        qrCodeFoundButton = findViewById(R.id.qrCodeFoundButton);
        qrCodeFoundButton.setVisibility(View.INVISIBLE);
        qrCodeFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), qrCode, Toast.LENGTH_SHORT).show();
                Log.i(MainActivity.class.getSimpleName(), "QR Code Found: " + qrCode);
            }
        });

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        requestCamera();
        //endregion
    }

    //region requestCamera
    private void requestCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(QRScanner.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //endregion

    //region startCamera
    private void startCamera() {
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
        previewView.setPreferredImplementationMode(PreviewView.ImplementationMode.SURFACE_VIEW);

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new QRCodeImageAnalyzer(new QRCodeFoundListener() {
            @Override
            public void onQRCodeFound(String _qrCode) {
                //todo
                // I would really like to change this part.
                // I would like it if instead of becoming visible, it immediately performs an action determined by the QR code.
                qrCode = _qrCode;
                qrCodeFoundButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void qrCodeNotFound() {
                qrCodeFoundButton.setVisibility(View.INVISIBLE);
            }
        }));

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageAnalysis, preview);
    }
    //endregion

    //region database methods
    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
    //endregion

    //region OnClickListeners
    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class); //todo
        }
    };

    private View.OnClickListener goHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };

    //endregion
}
package com.example.nidoqueue.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.nidoqueue.R;
//import com.google.zxing.WriterException;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

//import androidmads.library.qrgenearator.QRGContents;
//import androidmads.library.qrgenearator.QRGEncoder;

/** This is a super general QR class. Not 100% sure how exactly I want to implement
 * the QR codes. A part of me is thinking it might even be best to make them as a
 * fragment.
 */

public class QRActivity extends AppCompatActivity {

    //region class variables
    private ImageView qrImage;
    private Bitmap bitmap;
    //private QRGEncoder qrgEncoder;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_display);

        qrImage = findViewById(R.id.QRcode);
        /*

        qrgEncoder = new QRGEncoder("REPLACE ME!",
                null, QRGContents.Type.TEXT, 300);

        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(bitmap);
        }catch (WriterException e) {
            e.printStackTrace();
        }
        */
    }
}
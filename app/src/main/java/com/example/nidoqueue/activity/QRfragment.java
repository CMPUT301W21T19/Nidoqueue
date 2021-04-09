package com.example.nidoqueue.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nidoqueue.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

/**
 * Classname:   QRfragment.java
 * Version:     final
 * Date:        April 8th, 2021
 * Purpose:     fragment to display QR codes
 * Issues:      None
 */

public class QRfragment extends DialogFragment {

    //region class variables
    private ImageView qrImage;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private String data;
    //endregion

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.qr_display, null);
        data = getArguments().getString("QR");
        qrImage = view.findViewById(R.id.QRcode);
        qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 300);

        //region qrImage.setImageBitmap(bitmap)
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }
        //endregion

        //region return Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("QR Code")
                .setPositiveButton("Done", null)
                .create();
        //endregion
    }

}

//region usage instructions
// Use the following code to create a QRfragment
    //String data = StringToEncode;
    //Bundle bundle = new Bundle();
    //bundle.putString("QR", data);
    //QRFragment qrFragment = new QRFragment();
    //qrFragment.setArguments(bundle);
    //qrFragment.show(getSupportFragmentManager(), null);
//endregion
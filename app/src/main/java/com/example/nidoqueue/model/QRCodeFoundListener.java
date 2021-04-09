package com.example.nidoqueue.model;

/**
 * Classname:   QRCodeFoundListener.java
 * Sources:     based off of tutorial by learntodroid
 *              How to Create a QR Code Scanner App in Android
 *              https://learntodroid.com/how-to-create-a-qr-code-scanner-app-in-android/
 * Version:     final
 * Date:        April 9th, 2021
 * Purpose:     interface to help with scanning QR codes
 * Issues:      None
 */
public interface QRCodeFoundListener {
    void onQRCodeFound(String qrCode);
    void qrCodeNotFound();
}

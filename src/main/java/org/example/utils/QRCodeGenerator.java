package org.example.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.example.persistence.entity.UserEntity;
import org.example.presentation.dto.UserDTO;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class QRCodeGenerator {
    public static void generateQRCode(UserDTO userEntity) throws WriterException, IOException {

        String qrCodePath = "src/main/resources/QRCode/";
        String qrCodeName = qrCodePath + userEntity.getId() + userEntity.getFirstName() + "-QRCODE.png";

        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID: " + userEntity.getId()
                        + "\nName: " + userEntity.getFirstName() + " " + userEntity.getLastName()
                        + "\nEmail: " + userEntity.getEmail()
                        + "\nMobile: " + userEntity.getMobile(), BarcodeFormat.QR_CODE, 350, 350);

        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
    public static String generateQRCodeMail(UserDTO userEntity) throws WriterException, IOException {

        String qrCodePath = "src/main/resources/QRCode/";
        String qrCodeName = qrCodePath + userEntity.getId() + userEntity.getFirstName() + "-QRCODE.png";

        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "ID: " + userEntity.getId()
                        + "\nName: " + userEntity.getFirstName() + " " + userEntity.getLastName()
                        + "\nEmail: " + userEntity.getEmail()
                        + "\nMobile: " + userEntity.getMobile(), BarcodeFormat.QR_CODE, 350, 350);

        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return qrCodeName;
    }
}

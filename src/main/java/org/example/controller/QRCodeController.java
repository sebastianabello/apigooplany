package org.example.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api/v1")
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/qrcode")
    public void generateQRCode(HttpServletResponse response,
                               @RequestParam String text,
                               @RequestParam(defaultValue = "350") int width,
                               @RequestParam(defaultValue = "350") int height) throws Exception {
        BufferedImage image = qrCodeService.generateQRCode(text, width, height);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

}

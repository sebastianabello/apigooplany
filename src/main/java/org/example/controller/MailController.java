package org.example.controller;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.example.domain.EmailDTO;
import org.example.domain.EmailFileDTO;
import org.example.domain.User;
import org.example.service.IEmailService;
import org.example.service.UserService;
import org.example.utils.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MailController {

    @Autowired
    private IEmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveMail(@RequestBody EmailDTO emailDTO) {

        System.out.println("Mensaje Recibido" + emailDTO);

         emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getText());

         Map<String, String> response = new HashMap<>();
         response.put("estado", "Mail Enviado");

         return ResponseEntity.ok(response);
    }

    @PostMapping("/sendMessageWithFile")
    public ResponseEntity<?> receiveMailWithFile(@ModelAttribute EmailFileDTO emailFileDTO) {

        System.out.println("Mensaje Recibido" + emailFileDTO);

        try {
            String fileName = emailFileDTO.getFile().getOriginalFilename();

            Path path = Paths.get("src/main/resources/files/" + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.sendEmailWithFile(emailFileDTO.getToUser(), emailFileDTO.getSubject(), emailFileDTO.getText(), file);

            Map<String, String> response = new HashMap<>();
            response.put("estado", "Mail Enviado con archivo adjunto");
            response.put("archivo", fileName);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al enviar el correo");
        }
    }
    @PostMapping("/sendMessageAll")
    public ResponseEntity<?> receiveMailAll(@RequestBody EmailDTO emailDTO) {

        System.out.println("Mensaje Recibido" + emailDTO);

        // Obtener todos los usuarios
        List<User> users = userService.getUsers();

        // Extraer los correos electrónicos de los usuarios
        String[] emails = users.stream()
                .map(User::getEmail)
                .toArray(String[]::new);

        // Enviar el correo a todos los usuarios
        emailService.sendEmail(emails, emailDTO.getSubject(), emailDTO.getText());

        Map<String, String> response = new HashMap<>();
        response.put("estado", "Mail Enviado");

        return ResponseEntity.ok(response);

    }
    @PostMapping("/sendMessageAllWithQR")
    public ResponseEntity<?> receiveMailAllWithQR(@RequestBody EmailDTO emailDTO) {

        System.out.println("Mensaje Recibido" + emailDTO);

        // Obtener todos los usuarios
        List<User> users = userService.getUsers();

        for (User user : users) {
            // Generar el código QR para el usuario
            String qrCodePath;
            try {
                qrCodePath = QRCodeGenerator.generateQRCodeMail(user);
            } catch (WriterException | IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Error al generar el código QR");
            }

            // Enviar el correo al usuario con el código QR adjunto
            emailService.sendEmailWithFile(new String[]{user.getEmail()}, emailDTO.getSubject(), emailDTO.getText(), new File(qrCodePath));
        }

        Map<String, String> response = new HashMap<>();
        response.put("estado", "Mail Enviado");

        return ResponseEntity.ok(response);
    }
}

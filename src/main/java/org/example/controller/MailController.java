package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.EmailDTO;
import org.example.domain.EmailFileDTO;
import org.example.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class MailController {

    @Autowired
    private IEmailService emailService;

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
            response.put("estado", "Mail Enviado");
            response.put("archivo", fileName);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al enviar el correo");
        }



    }

}

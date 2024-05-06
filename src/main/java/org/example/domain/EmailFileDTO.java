package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailFileDTO {

    private String[] toUser;
    private String subject;
    private String text;
    private MultipartFile file;
}

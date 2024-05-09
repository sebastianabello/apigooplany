package org.example.services.notification;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDTO {

    private String[] toUser;
    private String subject;
    private String text;

}

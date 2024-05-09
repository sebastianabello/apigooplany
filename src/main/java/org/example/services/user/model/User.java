package org.example.services.user.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String mobile;
    private Long eventId;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Profile profile;
}

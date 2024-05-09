package org.example.services.user;


public record UserDTO(Long id,
                      String firstName,
                      String lastName,
                      String email,
                      String mobile,
                      Long eventId) {
}

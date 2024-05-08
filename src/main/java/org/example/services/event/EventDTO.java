package org.example.services.event;

import org.example.services.user.UserDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record EventDTO(Long id,
                       String name,
                       String location,
                       LocalDateTime date,
                       Long companyId,
                       List<UserDTO> users) {
    public EventDTO(Long id, String name, String location, LocalDateTime date, Long companyId) {
        this(id, name, location, date, companyId ,new ArrayList<>());
    }
}

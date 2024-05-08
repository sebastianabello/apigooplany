package org.example.services.company;

import org.example.services.event.EventDTO;

import java.util.ArrayList;
import java.util.List;

public record CompanyDTO(Long id,
                         String name,
                         String address,
                         List<EventDTO> events) {
    public CompanyDTO(Long id, String name, String address) {
        this(id,name,address,new ArrayList<>());
    }
}

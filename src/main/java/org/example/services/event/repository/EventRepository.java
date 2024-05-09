package org.example.services.event.repository;

import org.example.services.event.EventDTO;
import org.example.services.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("""
           SELECT new org.example.services.event.EventDTO(e.id, e.name, e.location,e.date,e.companyId)
           FROM Event e
           WHERE e.id = :id
           """)
    EventDTO findDTOById(Long id);

    @Query("""
           SELECT new org.example.services.event.EventDTO(e.id, e.name, e.location,e.date,e.companyId)
           FROM Event e
           WHERE e.companyId = :companyId
           """)
    List<EventDTO> findByCompanyId(Long companyId);


    void deleteByCompanyId(Long companyId);


}

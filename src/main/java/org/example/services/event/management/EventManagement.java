package org.example.services.event.management;

import lombok.RequiredArgsConstructor;
import org.example.services.event.EventDTO;
import org.example.services.event.EventExternalAPI;
import org.example.services.event.EventInternalAPI;
import org.example.services.event.mapper.EventMapper;
import org.example.services.event.repository.EventRepository;
import org.example.services.user.UserDTO;
import org.example.services.user.UserInternalAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventManagement implements EventInternalAPI, EventExternalAPI {

    private static final Logger LOG = LoggerFactory.getLogger(EventManagement.class);
    private final EventRepository repository;
    private final UserInternalAPI userInternalAPI;
    private EventMapper mapper;

    @Override
    public EventDTO getEventByIdWithUsers(Long id) {
        EventDTO ev = repository.findDTOById(id);
        List<UserDTO> dtos = userInternalAPI.getUsersByEventId(id);
        ev.users().addAll(dtos);
        return ev;
    }

    @Override
    public EventDTO add(EventDTO event) {
        return mapper.eventToEventDTO(repository.save(mapper.eventDTOToEvent(event)));
    }

    @Override
    public List<EventDTO> getEventsByCompanyId(Long id) {
        return repository.findByCompanyId(id);
    }

    @Override
    public List<EventDTO> getEventsByCompanyIdWithUsers(Long id) {
        List<EventDTO> events = repository.findByCompanyId(id);
        for (EventDTO event : events) {
            event.users().addAll(userInternalAPI.getUsersByEventId(event.id()));
        }
        return events;
    }
}

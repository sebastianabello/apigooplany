package org.example.services.event;

public interface EventExternalAPI {
    EventDTO getEventByIdWithUsers(Long id);
    EventDTO add(EventDTO event);
}

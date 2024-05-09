package org.example.services.event;

import java.util.List;

public interface EventInternalAPI {

    List<EventDTO> getEventsByCompanyId(Long id);
    List<EventDTO> getEventsByCompanyIdWithUsers(Long id);
}

package org.example.services.event.mapper;

import org.example.services.event.EventDTO;
import org.example.services.event.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {
    EventDTO eventToEventDTO(Event event);
    Event eventDTOToEvent(EventDTO eventDTO);
}

package org.example.services.user.mapper;

import org.example.services.user.ProfileDTO;
import org.example.services.user.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {
    ProfileDTO profileToProfileDTO(Profile profile);
    Profile profileDTOToProfile(ProfileDTO profileDTO);
}

package org.example.services.user;

import java.util.List;

public interface UserInternalAPI {

    List<UserDTO> getUsersByEventId(Long id);

    ProfileDTO getProfile(Long userId);
    ProfileDTO updateProfile(Long userId, ProfileDTO profile);

}

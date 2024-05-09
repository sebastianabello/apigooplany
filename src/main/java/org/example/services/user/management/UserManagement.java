package org.example.services.user.management;

import lombok.RequiredArgsConstructor;
import org.example.services.user.ProfileDTO;
import org.example.services.user.UserDTO;
import org.example.services.user.UserExternalAPI;
import org.example.services.user.UserInternalAPI;
import org.example.services.user.mapper.ProfileMapper;
import org.example.services.user.mapper.UserMapper;
import org.example.services.user.model.Profile;
import org.example.services.user.model.User;
import org.example.services.user.repository.ProfileRepository;
import org.example.services.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserManagement  implements UserInternalAPI, UserExternalAPI {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private static final Logger LOG = LoggerFactory.getLogger(UserManagement.class);

    @Override
    @Transactional
    public UserDTO add(UserDTO user) {
        User usr = userMapper.userDTOToUser(user);
        return userMapper.userToUserDTO(userRepository.save(usr));
    }

    @Override
    public List<UserDTO> getUsersByEventId(Long eventId) {
        return userRepository.findByEventId(eventId);
    }

    @Override
    public ProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return profileMapper.profileToProfileDTO(user.getProfile());
    }

    @Override
    @Transactional
    public ProfileDTO updateProfile(Long userId, ProfileDTO profileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Profile profile = profileMapper.profileDTOToProfile(profileDTO);
        profile.setUser(user);
        return profileMapper.profileToProfileDTO(profileRepository.save(profile));
    }
}

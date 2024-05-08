package org.example.services.notification.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.services.notification.IUserDAO;
import org.example.services.user.model.User;
import org.example.services.notification.presentation.dto.UserDTO;
import org.example.services.notification.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    @Override
    public List<UserDTO> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        return this.userDAO.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> userEntity = this.userDAO.findById(id);
        if (userEntity.isPresent()) {
            return new ModelMapper().map(userEntity.get(), UserDTO.class);
        } else {
            return new UserDTO();
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            User user = modelMapper.map(userDTO, User.class);
            this.userDAO.saveUser(user);
            return userDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al crear el usuario");
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userEntity = this.userDAO.findById(id);

        if (userEntity.isPresent()) {
            User userToUpdate = userEntity.get();
            userToUpdate.setFirstName(userDTO.getFirstName());
            userToUpdate.setLastName(userDTO.getLastName());
            userToUpdate.setEmail(userDTO.getEmail());
            userToUpdate.setMobile(userDTO.getMobile());
            this.userDAO.updateUser(userToUpdate);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(userToUpdate, UserDTO.class);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> userEntity = this.userDAO.findById(id);
        if (userEntity.isPresent()) {
            this.userDAO.deleteUser(userEntity.get());
            return "Usuario con ID " + id + " ha sido eliminado";
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }
}

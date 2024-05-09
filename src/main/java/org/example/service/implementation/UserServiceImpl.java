package org.example.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.persistence.dao.interfaces.IUserDAO;
import org.example.persistence.entity.UserEntity;
import org.example.presentation.dto.UserDTO;
import org.example.service.interfaces.IUserService;
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
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<UserEntity> userEntity = this.userDAO.findById(id);
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
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            this.userDAO.saveUser(userEntity);
            return userDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al crear el usuario");
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<UserEntity> userEntity = this.userDAO.findById(id);

        if (userEntity.isPresent()) {
            UserEntity userEntityToUpdate = userEntity.get();
            userEntityToUpdate.setFirstName(userDTO.getFirstName());
            userEntityToUpdate.setLastName(userDTO.getLastName());
            userEntityToUpdate.setEmail(userDTO.getEmail());
            userEntityToUpdate.setMobile(userDTO.getMobile());
            this.userDAO.updateUser(userEntityToUpdate);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(userEntityToUpdate, UserDTO.class);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public String deleteUser(Long id) {
        Optional<UserEntity> userEntity = this.userDAO.findById(id);
        if (userEntity.isPresent()) {
            this.userDAO.deleteUser(userEntity.get());
            return "Usuario con ID " + id + " ha sido eliminado";
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }
}

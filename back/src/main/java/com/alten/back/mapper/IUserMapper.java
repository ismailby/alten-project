package com.alten.back.mapper;


import java.util.List;

import org.mapstruct.Mapper;

import com.alten.back.dto.UserDTO;
import com.alten.back.model.User;





/**
 * Interface pour le mappage entre l'entite {@link User} et le DTO {@link UserDTO}.
 */

@Mapper(componentModel = "spring")
public interface IUserMapper {

	
	 // Conversion de Product en UserDTO
    UserDTO userToUserDTO(User user);
    
    // Conversion de UserDTO en User
    User userDTOToUser(UserDTO userDTO);
    
    List<UserDTO> mapListOfUserEntity(List<User> user);
}

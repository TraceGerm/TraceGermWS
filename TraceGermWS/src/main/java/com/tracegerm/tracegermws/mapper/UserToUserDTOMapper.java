package com.tracegerm.tracegermws.mapper;

import com.tracegerm.tracegermws.dto.UserDTO;
import com.tracegerm.tracegermws.model.user.User;

public class UserToUserDTOMapper implements IMapper<User, UserDTO>{

	@Override
	public UserDTO map(User source, UserDTO target) {
		if(source.getUsername() != null){
			target.setUsername(source.getUsername());
		}
		return target;
	}

}

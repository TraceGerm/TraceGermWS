package com.tracegerm.tracegermws.mapper;

import com.tracegerm.tracegermws.dto.UserDTO;
import com.tracegerm.tracegermws.model.user.User;

public class UserDTOtoUserMapper implements IMapper<UserDTO, User>{

	@Override
	public User map(UserDTO source, User target) {
		if(source.getUsername() != null){
			target.setUsername(source.getUsername());
		}
		return target;
	}

}

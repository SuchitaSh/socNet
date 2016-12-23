package com.socnet.service.propertymaps;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import com.socnet.dto.UserWithFollowingStatusDto;
import com.socnet.dto.enums.UserStatus;
import com.socnet.persistence.entities.User;

public class UserWithStatusMap extends PropertyMap<User, UserWithFollowingStatusDto> {

	private User currentUser;
	
	public UserWithStatusMap(User currentUser) {
		this.currentUser = currentUser;
	}
	
	@Override
	protected void configure() {
		
	Converter<Long, UserStatus> conv = new AbstractConverter<Long, UserStatus>() {
		protected UserStatus convert(Long id) {
			return getStatus(id);
		}
	};
		using(conv).map(source.getId()).setStatus(null);
	}
	
	private UserStatus getStatus(Long id){
				
		if(currentUser.isFriend(id)){
			return UserStatus.FRIEND;
		}
		else if(currentUser.isFollower(id)){
			return UserStatus.FOLLOWER;
			}
		else if(currentUser.isFollowing(id)){
			return UserStatus.FOLLOWING;
		}
		else{
			return UserStatus.UNKNOWN;
		}
	
	}

}

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
		
	Converter<Long, Boolean> followingConverter = new AbstractConverter<Long, Boolean>() {
		protected Boolean convert(Long id) {
			return currentUser.isFollowing(id);
		}
	};
	
	Converter<Long, Boolean> followerConverter = new AbstractConverter<Long, Boolean>() {
		protected Boolean convert(Long id) {
			return currentUser.isFollower(id);
		}
	};
	
		using(followingConverter).map(source.getId()).setFollowing(false);
		using(followerConverter).map(source.getId()).setFollower(false);
	}
	
	

}

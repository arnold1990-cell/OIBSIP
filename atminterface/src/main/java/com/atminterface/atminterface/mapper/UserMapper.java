package com.atminterface.atminterface.mapper;

import com.atminterface.atminterface.dto.UserDto;
import com.atminterface.atminterface.model.User;

import java.util.UUID;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Mapper class for converting between User entity and UserDto
 */
public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;

        return new UserDto(
                user.getId() != null ? user.getId() : UUID.randomUUID(),
                user.getUser_id(),
                user.getFullName(),
                user.getEmail(),
                AccountMapper.toDtoList(user.getAccounts())
        );
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) return null;

        User user = new User();
        user.setId(userDto.id());
        user.setUser_id(userDto.user_id());
        user.setFullName(userDto.fullName());
        user.setEmail(userDto.email());
        user.setAccounts(AccountMapper.toEntityList(userDto.accounts()));
        return user;
    }
}

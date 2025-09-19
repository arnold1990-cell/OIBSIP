package com.atminterface.atminterface.mapper;

import com.atminterface.atminterface.dto.UserDto;
import com.atminterface.atminterface.model.AppUser;

import java.util.UUID;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Mapper class for converting between User entity and UserDto
 */
public class UserMapper {

    public static UserDto toDto(AppUser appUser) {
        if (appUser == null) return null;

        return new UserDto(
                appUser.getId() != null ? appUser.getId() : UUID.randomUUID(),
                appUser.getUserid(),
                appUser.getFullName(),
                appUser.getEmail(),
                AccountMapper.toDtoList(appUser.getAccounts())
        );
    }

    public static AppUser toEntity(UserDto userDto) {
        if (userDto == null) return null;

        AppUser appUser = new AppUser();
        appUser.setId(userDto.id());
        appUser.setUserid(userDto.userid());
        appUser.setFullName(userDto.fullName());
        appUser.setEmail(userDto.email());
        appUser.setAccounts(AccountMapper.toEntityList(userDto.accounts()));
        return appUser;
    }
}

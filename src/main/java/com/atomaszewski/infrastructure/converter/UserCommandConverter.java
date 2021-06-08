package com.atomaszewski.infrastructure.converter;

import com.atomaszewski.infrastructure.model.ExternalUserMessage;
import com.atomaszewski.infrastructure.model.UserCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserCommandConverter {

    @Mapping(target = "avatarUrl", source = "avatar_url")
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "publicRepos", source = "public_repos")
    UserCommand convert(final ExternalUserMessage message);
}

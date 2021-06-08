package com.atomaszewski.domain.converter;

import com.atomaszewski.application.UserRepresentation;
import com.atomaszewski.infrastructure.model.UserCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserConverter {

    @Mapping(target = "calculations", ignore = true)
    UserRepresentation convert(final UserCommand userCommand);
}

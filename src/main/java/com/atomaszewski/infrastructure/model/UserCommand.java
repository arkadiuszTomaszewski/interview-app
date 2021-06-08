package com.atomaszewski.infrastructure.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserCommand {
    private static final long serialVersionUID = -1870651179746760255L;

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private Long followers;
    private Long publicRepos;
}

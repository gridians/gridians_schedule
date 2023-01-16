package com.schedule.github.dto;

import com.schedule.github.entity.Github;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GithubResponseDto {
    private String login;
    private String name;
    private Long id;
    private String avatar_url;
    private String html_url;
    private Long followers;
    private Long following;
    private String location;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static Github convert(GithubResponseDto githubResponseDto) {
        Github github = Github.builder()
                .id(githubResponseDto.id)
                .name(githubResponseDto.name)
                .login(githubResponseDto.login)
                .profileImageUrl(githubResponseDto.avatar_url)
                .url(githubResponseDto.html_url)
                .followings(githubResponseDto.following)
                .followers(githubResponseDto.followers)
                .createdAt(githubResponseDto.created_at)
                .location(githubResponseDto.location)
                .modifiedAt(githubResponseDto.updated_at)
                .build();

        return github;
    }
}

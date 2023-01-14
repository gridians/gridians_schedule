package com.schedule.github;

import com.schedule.github.entity.Github;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubResponseDto {
    private String login;
    private Long id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private String site_admin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    private String twitter_username;
    private String public_repos;
    private String public_gists;
    private String followers;
    private Long following;
    private String created_at;
    private String updated_at;

    public static Github convert(GithubResponseDto githubResponseDto) {
        Github github = Github.builder()
                .name(githubResponseDto.login)
                .build();

        return github;
    }
}

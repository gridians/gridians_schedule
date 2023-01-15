package com.schedule.github.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Github {

    @Id
    @Column(name = "github_id")
    private Long id;

    private String name;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    private String url;

    @Column(name = "followers_url")
    private String followersUrl;

    @Column(name = "following_url")
    private String followingUrl;

    private String location;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public void update(Github github) {
        this.name = github.name;
        this.profileImageUrl = github.profileImageUrl;
        this.url = github.url;
        this.followersUrl = github.followersUrl;
        this.followingUrl = github.followingUrl;
        this.location = github.location;
        this.createdAt = github.createdAt;
        this.modifiedAt = github.modifiedAt;
    }
}
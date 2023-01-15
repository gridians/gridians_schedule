package com.schedule.github.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "followers")
    private Integer followers;

    @Column(name = "followings")
    private Integer followings;

    @Setter
    @Column(name = "recent_commit_message")
    private String recentCommitMessage;

    @Setter
    @Column(name = "recent_commit_at")
    private LocalDateTime recentCommitAt;

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
        this.followers = github.followers;
        this.followings = github.followings;
        this.recentCommitMessage = github.recentCommitMessage;
        this.recentCommitAt = github.recentCommitAt;
        this.location = github.location;
        this.createdAt = github.createdAt;
        this.modifiedAt = github.modifiedAt;
    }
}
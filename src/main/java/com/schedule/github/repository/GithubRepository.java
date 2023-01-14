package com.schedule.github.repository;

import com.schedule.github.entity.Github;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubRepository extends JpaRepository<Github, Long> {
}

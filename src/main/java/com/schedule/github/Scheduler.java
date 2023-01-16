package com.schedule.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedule.github.entity.Github;
import com.schedule.github.repository.GithubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class Scheduler {

    private static final int THREAD_MAX = 10;
    private final ObjectMapper objectMapper;
    private final GithubRepository githubRepository;

    public static void updateGithub(Github github, Github updateGithub) {
        github.update(updateGithub);
    }

    @Scheduled(fixedDelay = 300000)
//    @Scheduled(fixedDelay = 10000)
    public void getGithubUserInfo() throws Exception {
        List<Github> githubs = githubRepository.findAll();

        List<Thread> threads = new ArrayList<>();
        int i = 0;
        for (i = 0; i < githubs.size(); i += THREAD_MAX) {
            if(i < githubs.size() && githubs.size() < i + THREAD_MAX) {
                Thread thread = new Thread(new GithubRequestThread(githubs.subList(i, githubs.size() - i), objectMapper));
                thread.start();
                threads.add(thread);
                break;
            }
            Thread thread = new Thread(new GithubRequestThread(githubs.subList(i, THREAD_MAX), objectMapper));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception exception) {
            }
        }
    }
}
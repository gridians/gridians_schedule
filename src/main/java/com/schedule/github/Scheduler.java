package com.schedule.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedule.github.entity.Github;
import com.schedule.github.repository.GithubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final ObjectMapper objectMapper;
    private final GithubRepository githubRepository;
    private static List<Github> resultGithubs = new ArrayList<>();

    public static void saveGithubInfo(Github github){
        resultGithubs.add(github);
    }

    @Scheduled(fixedDelay = 300000)
    public void getGithubUserInfo() throws Exception {
        List<Github> githubs = githubRepository.findAll();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < githubs.size(); i += 10) {
            Thread thread = new Thread(new GithubRequestThread(githubs.subList(i, 1), objectMapper));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception exception) {
            }
            githubRepository.saveAll(githubs);
            resultGithubs.clear();
        }
    }
}
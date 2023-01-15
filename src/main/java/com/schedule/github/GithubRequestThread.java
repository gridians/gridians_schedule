package com.schedule.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedule.github.dto.GithubResponseDto;
import com.schedule.github.entity.Github;
import com.schedule.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class GithubRequestThread implements Runnable{

    private static final String GITHUB_USER_URL = "https://api.github.com/users/";
    private static final String GITHUB_USER_EVENT = "/events/public?page=1&per_page=1";
    private static final String GITHUB_USER_REPO = "/repos";
    private ObjectMapper objectMapper;
    private List<Github> githubs;
    public GithubRequestThread(List<Github> githubs, ObjectMapper objectMapper){
        this.githubs = githubs;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run() {
        for(Github github : githubs){
            try {
                String githubUserResponse = ApiUtils.request(GITHUB_USER_URL + github.getName(), "GET");

                GithubResponseDto responseDto = objectMapper.readValue(githubUserResponse, GithubResponseDto.class);

                Github findGithub = GithubResponseDto.convert(responseDto);
                Scheduler.updateGithub(github, findGithub);
            } catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("thread exception");
            }
        }
    }
}

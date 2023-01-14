package com.schedule.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.schedule.github.entity.Github;
import com.schedule.github.repository.GithubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Transactional
public class GithubRequestThread implements Runnable{
    private ObjectMapper objectMapper;
    private List<Github> githubs;
    public GithubRequestThread(List<Github> githubs, ObjectMapper objectMapper){
        this.githubs = githubs;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run() {
        HttpURLConnection connection;
        for(Github github : githubs){
            try {
                URL url = new URL("https://api.github.com/users/" + github.getName());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);

                if (connection.getResponseCode() / 100 != 2) {
                    InputStream errorStream = connection.getErrorStream();
                    Scanner s = new Scanner(errorStream).useDelimiter("\\A");
                    String error = s.hasNext() ? s.next() : "";

                    System.out.println(error);
                    throw new RuntimeException("Bad Request Exception");
                }

                InputStream responseStream = connection.getInputStream();

                Scanner s = new Scanner(responseStream).useDelimiter("\\A");
                String response = s.hasNext() ? s.next() : "";


                GithubResponseDto responseDto = objectMapper.readValue(response, GithubResponseDto.class);


                Github findGithub = GithubResponseDto.convert(responseDto);
                github.update(findGithub);
                Scheduler.saveGithubInfo(github);
            } catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("thread exception");
            }
        }
    }
}

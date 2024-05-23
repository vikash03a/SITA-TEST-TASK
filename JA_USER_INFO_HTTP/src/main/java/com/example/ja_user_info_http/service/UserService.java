package com.example.ja_user_info_http.service;

import com.example.ja_user_info_http.model.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
public class UserService {

    @Value("${post.user.info.url}")
    private String postUserInfoUrl;

    public UserResponse getUserDetail(String username) {
        Properties properties = new Properties();
        try {
            ClassPathResource resource = new ClassPathResource("users.properties");
            properties.load(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            String workstation = properties.getProperty(username);
            if (workstation != null) {
                UserResponse response = new UserResponse();
                response.setUser(username);
                response.setWorkstation(workstation);
                response.setStatus("Success");
                response.setMessage("user exist in database and has access to given workstation");
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void postUserDetail(UserResponse userResponse) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(postUserInfoUrl, userResponse, String.class);
    }
}

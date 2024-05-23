package com.example.ja_user_info_http.service;

import com.example.ja_user_info_http.model.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(userService, "postUserInfoUrl", "http://localhost:8080/userDetail");
    }

    @Test
    public void testGetUserDetail() {
        UserResponse response = userService.getUserDetail("admin");
        assertEquals("admin", response.getUser());
        assertEquals("CCUICKB0F1", response.getWorkstation());
        assertEquals("Success", response.getStatus());
        assertEquals("user exist in database and has access to given workstation", response.getMessage());
    }

    @Test
    public void testPostUserDetail() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUser("admin");
        userResponse.setWorkstation("CCUICKB0F1");
        userResponse.setStatus("Success");
        userResponse.setMessage("user exist in database and has access to given workstation");

        userService.postUserDetail(userResponse);

        verify(restTemplate, times(1)).postForObject(eq("http://localhost:8080/userDetail"), eq(userResponse), eq(String.class));
    }
}

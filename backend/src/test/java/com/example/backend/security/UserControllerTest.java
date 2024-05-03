package com.example.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    //Simuliere einen Request auf das Backend
    @Autowired
    MockMvc mvc;

    @Test
    void getMe_shouldReturn_anonymousUserWhenCalledWithoutLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/user/me2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("anonymousUser"));
    }

    @Test
    @WithMockUser(username = "testUser")
    void getMe_shouldReturn_testUserWhenCalledWithLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/user/me2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("testUser"));
    }

//    @Test
//    void getMe_shouldReturn_gitHubUser_WhenCalled() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/api/user/me")
//                        .with(oidcLogin().userInfoToken(token -> token
//                                .claim("login", "testUser")
//                                .claim("avatar_url", "testAvatarUrl")
//                        ))
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("gitHubUser"));
//    }
}
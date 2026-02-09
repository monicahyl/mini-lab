package com.example.minispringboot.service;

import static com.google.common.truth.Truth.assertThat;

import com.example.minispringboot.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.EmailService;
import service.UserService;

class UserServiceTest {

    @Test
    void testRegisterSuccess() {
        // Mock 外部依赖
        UserRepository mockRepo = Mockito.mock(UserRepository.class);
        EmailService mockEmail = Mockito.mock(EmailService.class);

        Mockito.when(mockRepo.existsByEmail("alice@example.com")).thenReturn(false);

        UserService service = new UserService(mockRepo, mockEmail);

        // 执行测试方法
        boolean result = service.register("Alice", "alice@example.com");

        // Truth 断言
        assertThat(result).isTrue();
        Mockito.verify(mockRepo).save(Mockito.any());
        Mockito.verify(mockEmail).sendWelcomeEmail("alice@example.com");
    }
}
package service;

import com.example.minispringboot.dao.UserRepository;
import com.example.minispringboot.entity.User;

public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository repo, EmailService email) {
        this.userRepository = repo;
        this.emailService = email;
    }

    public boolean register(String username, String email) {
        if (userRepository.existsByEmail(email)) {
            return false;
        }
        userRepository.save(new User(username, email));
        emailService.sendWelcomeEmail(email);
        return true;
    }
}
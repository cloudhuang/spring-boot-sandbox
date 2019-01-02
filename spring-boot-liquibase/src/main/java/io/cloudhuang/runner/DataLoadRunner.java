package io.cloudhuang.runner;

import io.cloudhuang.entity.User;
import io.cloudhuang.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class DataLoadRunner implements CommandLineRunner {
    private final UserRepository userRepository;

    public DataLoadRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Red", "Blue", "Gray", "Yello").forEach(s -> {
            userRepository.save(User.builder().username(s).build());
        });
    }
}

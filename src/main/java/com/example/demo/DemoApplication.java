package com.example.demo;

// ВНИМАТЕЛЬНО проверь эти пути!
import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {
            // Проверяем, если в базе пусто — добавляем игроков
            if (repository.count() == 0) {
                repository.save(new Player("Лионель Месси", "Нападающий"));
                repository.save(new Player("Криштиану Роналду", "Нападающий"));
                System.out.println("База данных инициализирована: Месси и Роналду добавлены!");
            }
        };
    }
}
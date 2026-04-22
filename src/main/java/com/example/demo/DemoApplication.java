package com.example.demo;

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
            // Если в базе пусто, добавляем легенд
            if (repository.count() == 0) {
                // Аргументы: id (null), имя, номер (Integer), клуб (null)
                repository.save(new Player(null, "Лионель Месси", 10, null));
                repository.save(new Player(null, "Криштиану Роналду", 7, null));

                System.out.println("--- БАЗА ИНИЦИАЛИЗИРОВАНА: МЕССИ И РОНАЛДУ ДОБАВЛЕНЫ ---");
            }
        };
    }
}
package project.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;

/**
 * @author Vladyslav Pustovalov
 * class which contains Configuration bot data
 */
@Configuration
public class BotConfig {
    private final String botName = System.getenv("botName");
    private final String botToken = System.getenv("botToken");
    private final DefaultBotOptions botOptions = new DefaultBotOptions();

    public String getBotName() {
        return botName;
    }

    @Bean
    public String getBotToken() {
        return botToken;
    }

    @Bean
    public DefaultBotOptions getBotOptions() {
        return botOptions;
    }
}

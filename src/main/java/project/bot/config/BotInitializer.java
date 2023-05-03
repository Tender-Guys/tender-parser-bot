package project.bot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import project.bot.service.TenderParserServiceBot;

/**
 * @author Vladyslav Pustovalov
 * class which registers the created bot
 */
@Component
@Slf4j
public class BotInitializer {
    private final String classError = "BotInitializer error occurred: ";
    private final BotConfig config = new BotConfig();
    private final TenderParserServiceBot bot = new TenderParserServiceBot(config.getBotOptions(), config.getBotToken());

    /**
     * Method which registers the created bot
     */
    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
            log.info("Bot " + bot.getBotUsername() + " is successfully registered");
        } catch (TelegramApiException e) {
            log.error(classError + e.getMessage());
        }
    }
}

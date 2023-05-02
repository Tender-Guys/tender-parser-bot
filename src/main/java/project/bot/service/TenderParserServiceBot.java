package project.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project.bot.config.BotConfig;
import project.bot.model.dao.HUserDao;
import project.bot.model.dao.IUserDao;
import project.bot.model.response.User;
import project.bot.util.BotMenuItems;

/**
 * @author Vladyslav Pustovalov
 * class TelegramBot which gets messages from useers and could answer to them
 */
@Component
@Slf4j
public class TenderParserServiceBot extends TelegramLongPollingBot {
    private final BotConfig config = new BotConfig();
    private final MessageSender messageSender = new MessageSender();
    private final IUserDao dao = new HUserDao();
    @Bean
    private IUserDao getDao() {
        return dao;
    }
    private final BotService service = new BotService(getDao());

    public TenderParserServiceBot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    /**
     * Method which gets updates from users and sends to them an answer
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String userName = message.getChat().getUserName();
        String messageText = message.getText();
        long chatId = message.getChatId();

        User user = new User();
        user.setChatId(Math.toIntExact(chatId));
        user.setFirstName(message.getChat().getFirstName());
        user.setLastName(message.getChat().getLastName());

        if (!update.hasMessage() || !update.getMessage().hasText()) {
            log.warn("Unexpected update from user " + userName);
            sendMessageToUser(messageSender.sendUnsupportedDataWarning(chatId));
            log.info("Warn message was sent to user " + userName);
        } else {
            switch (messageText) {
                case "/start" -> {
                    service.addNewUserToDB(user);
                    sendMessageToUser(messageSender.sendWelcomeMessage(chatId));
                    log.info("Welcome message was sent to user " + userName);
                }
                case BotMenuItems.USER_SUBSCRIPTIONS -> {
                    sendMessageToUser(messageSender.sendUserSubscriptionsList(chatId));
                    log.info("Subscription list was sent to user " + userName);

                }
                case BotMenuItems.AVAILABLE_SITES -> {
                    sendMessageToUser(messageSender.sendAvailableSitesList(chatId));
                    log.info("Available sites list was sent to user " + userName);
                }
                case BotMenuItems.HELP_INST -> {
                    sendMessageToUser(messageSender.sendHelpMessage(chatId));
                    log.info("Help instructions was sent to user " + userName);
                }
                default -> {
                    sendMessageToUser(messageSender.sendUnsupportedCommandWarning(chatId));
                    log.info("Unsupported command warning was sent to user " + userName);
                }
            }
        }
    }

    private void sendMessageToUser(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: "+e);
        }
    }
}
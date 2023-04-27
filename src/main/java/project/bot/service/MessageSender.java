package project.bot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author Vladyslav Pustovalov
 * class which consist of answers to a user from the Telegram Bot
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageSender {

    static final String WELCOME_TEXT = "Welcome text";
    static final String USER_SUBSCRIPTION_TEXT = "User's subscription list";
    static final String AVAILABLE_SITES_TEXT = "Available sites for subscription";
    static final String HELP_TEXT = "Help text";
    static final String DEFAULT_TEXT = "Sorry, this command is not supported!\nPlease use the Keyboard buttons!";
    static final String WARN_TEXT = "You are trying to make bad things!\nPlease use the Keyboard buttons!";
    static final KeyboardSetter keyboard = new KeyboardSetter();

    /**
     * Method which sends the welcome text to a user
     */
    public SendMessage startMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(WELCOME_TEXT);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends to a user the list with they subscription
     */
    public SendMessage userSubscriptionsList(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(USER_SUBSCRIPTION_TEXT);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends the list with tender sites available for the subscription
     */
    public SendMessage availableSitesList(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(AVAILABLE_SITES_TEXT);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends information and instructions for a user
     */
    public SendMessage helpMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(HELP_TEXT);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends default answer on not supported commands
     */
    public SendMessage defaultMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(DEFAULT_TEXT);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends default answer on not supported update date format
     */
    public SendMessage warnMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(WARN_TEXT);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }
}

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
    final String welcomeText = "Welcome text";
    final String usersSubscriptionsText = "User's subscription list";
    final String availableSitesText = "Available sites for subscription";
    final String helpText = "Help text";
    final String unsupportedCommandText = "Sorry, this command is not supported!\nPlease use the Keyboard buttons!";
    final String unsupportedDataWarningText = "You are trying to make bad things!\nPlease use the Keyboard buttons!";
    final KeyboardSetter keyboard = new KeyboardSetter();

    /**
     * Method which sends the welcome text to a user
     */
    public SendMessage sendWelcomeMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(welcomeText);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends to a user the list with they subscription
     */
    public SendMessage sendUserSubscriptionsList(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(usersSubscriptionsText);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends the list with tender sites available for the subscription
     */
    public SendMessage sendAvailableSitesList(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(availableSitesText);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends information and instructions for a user
     */
    public SendMessage sendHelpMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(helpText);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends default answer on not supported commands
     */
    public SendMessage sendUnsupportedCommandWarning(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(unsupportedCommandText);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends default answer on not supported update date format
     */
    public SendMessage sendUnsupportedDataWarning(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(unsupportedDataWarningText);
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }
}

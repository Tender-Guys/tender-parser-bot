package project.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladyslav Pustovalov
 * class which consist of keyboards which are viewed to a user in the Telegram Bot
 */
public class KeyboardSetter {
    final String userSubscriptionsButton = "My subscription list";
    final String availableSitesButton = "Available tender sites for subscription";
    final String helpButton = "Help instructions";

    /**
     * Method which initializes default keyboard for the bot
     */
    public ReplyKeyboardMarkup setDefaultKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        row1.add(userSubscriptionsButton);
        row2.add(availableSitesButton);
        row3.add(helpButton);
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);
        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }
}

package project.bot.model.dao;

import project.bot.model.response.User;

public interface IUserDao {

    User getByChatId(Integer chatId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}

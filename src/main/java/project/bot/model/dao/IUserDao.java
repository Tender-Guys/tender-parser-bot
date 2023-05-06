package project.bot.model.dao;

import project.bot.model.response.User;
import java.util.List;

public interface IUserDao {

    User getByChatId(Long chatId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    List<User> getAllUsers();
}

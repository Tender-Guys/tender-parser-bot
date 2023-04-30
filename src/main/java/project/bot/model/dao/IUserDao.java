package project.bot.model.dao;

import project.bot.model.dto.User;

public interface IUserDao {

    public User getByChatId(Integer chatId);

    public boolean addUser(User user);

    public void updateUser(User user);

    public boolean deleteUser(User user);
}

package project.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bot.model.dao.IUserDao;
import project.bot.model.response.User;
import java.util.List;

@Service
public class BotService {
    private final IUserDao dao;

    @Autowired
    public BotService(IUserDao dao) {
        this.dao = dao;
    }

    public User getUserFromDBbyChatId(Long chatId) {
        return dao.getByChatId(chatId);
    }

    public void addNewUserToDB(User user) {
        dao.addUser(user);
    }

    public void updateUserFromDB(User user) {
        dao.updateUser(user);
    }

    public void deleteUserFromDB(User user) {
        dao.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }
}

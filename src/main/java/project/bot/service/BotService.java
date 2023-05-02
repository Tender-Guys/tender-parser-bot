package project.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bot.model.dao.IUserDao;
import project.bot.model.response.User;

@Service
public class BotService {

    private final IUserDao dao;

    @Autowired
    public BotService(IUserDao dao) {
        this.dao = dao;
    }

    public User getUserFromDBbyChatId(Integer chatId) {
        return dao.getByChatId(chatId);
    }

    public void addNewUserToDB(User user) {
        dao.addUser(user);
    }
}

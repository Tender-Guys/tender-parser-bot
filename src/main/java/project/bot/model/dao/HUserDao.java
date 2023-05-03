package project.bot.model.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import project.bot.model.response.User;
import project.bot.util.HibernateSessionFactoryUtil;

@Slf4j
public class HUserDao implements IUserDao {
    private final String classError = "HUserDAO error occurred: ";

    @Override
    public User getByChatId(Integer chatId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            log.info("User with chatId: " + chatId + ", is successfully got from DB");
            return session.get(User.class, chatId);
        } catch (HibernateException e) {
            log.error(classError + e.getMessage());
        }
        return new User();
    }

    @Override
    public void addUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User existingUser = session.byNaturalId(User.class)
                    .load();
            if (existingUser == null) {
                session.getTransaction().begin();
                session.persist(user);
                session.getTransaction().commit();
            }
            log.info(user.toString() + ", is added to DB");
        } catch (HibernateException e) {
            log.error(classError + e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.merge(user);
            log.info(user.toString() + ", is updated in DB");
        } catch (HibernateException e) {
            log.error(classError + e.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) {
        if (getByChatId(user.getChatId()) == null) {
            log.info(user.toString() + " is already deleted");
        } else {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                session.getTransaction().begin();
                session.remove(user);
                session.getTransaction().commit();
                log.info(user.toString() + " is successfully deleted");
            } catch (HibernateException e) {
                log.error(classError + e.getMessage());
            }
        }
    }
}

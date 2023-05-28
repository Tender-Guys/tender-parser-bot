package project.bot.model.dao;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import project.bot.model.response.User;
import project.bot.util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HUserDao implements IUserDao {
    private final String classError = "HUserDAO error occurred: ";

    @Override
    public User getByChatId(Long chatId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, chatId);
            log.info("User with chatId: " + chatId + ", is successfully got from DB");
            return user;
        } catch (HibernateException e) {
            log.error(classError + e.getMessage());
        }
        return new User();
    }

    @Override
    public void addUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User existingUser = session.bySimpleNaturalId(User.class).load(user.getChatId());
            if (existingUser == null) {
                session.getTransaction().begin();
                session.persist(user);
                session.getTransaction().commit();
                log.info(user.toString() + ", is added to DB");
            } else {
                log.info(user.toString() + "is already in the DB");
            }
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

    @Override
    public List<User> getAllUsers() {
        List<User> allUsersList = new ArrayList<>();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaQuery<User> cq = session.getCriteriaBuilder()
                    .createQuery(User.class);
            Root<User> rootEntry = cq.from(User.class);
            CriteriaQuery<User> all = cq.select(rootEntry);
            allUsersList = session.createQuery(all)
                    .getResultList();
            log.info("All users are got from DB");
        } catch (HibernateException e) {
            log.error(classError + e.getMessage());
        }
        return allUsersList;
    }
}

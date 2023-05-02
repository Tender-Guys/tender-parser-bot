package project.bot.model.dao;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import project.bot.model.response.User;
import project.bot.util.HibernateSessionFactoryUtil;

public class HUserDao implements IUserDao {
    @Override
    public User getByChatId(Integer chatId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(User.class, chatId);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new User();
    }

    @Override
    public boolean addUser(User user) {
        Boolean isAdded = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User existingUser = (User) session.byNaturalId(User.class)
                    .load();
            if (existingUser == null) {
                session.getTransaction().begin();
                session.persist(user);
                session.getTransaction().commit();
                isAdded = true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public void updateUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.merge(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteUser(User user) {
        Boolean isDeleted = false;
        if (getByChatId(user.getChatId()) == null) {
            return isDeleted;
        }
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.remove(user);
            session.getTransaction().commit();
            isDeleted = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}

package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.User;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Predicate idPredicate = cb.equal(root.get("id"), id);
            return session.createQuery(criteriaQuery.where(idPredicate)).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error find user by id", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            root.fetch("roles");
            Predicate emailPredicate = cb.equal(root.get("email"), email);
            return session.createQuery(criteriaQuery.where(emailPredicate)).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error find user by email", e);
        }
    }
}

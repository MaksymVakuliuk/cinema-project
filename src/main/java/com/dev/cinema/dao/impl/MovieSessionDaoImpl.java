package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert MovieSession entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession findById(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(MovieSession.class);
            Root<MovieSession> root = criteriaQuery.from(MovieSession.class);
            root.fetch("movie");
            root.fetch("cinemaHall");
            Predicate idPredicate = cb.equal(root.get("id"), id);
            return session.createQuery(criteriaQuery.where(idPredicate)).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving MovieSession by id", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSession(Long movieId, LocalDate date) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(MovieSession.class);
            Root<MovieSession> root = criteriaQuery.from(MovieSession.class);
            root.fetch("movie");
            root.fetch("cinemaHall");
            Predicate moviePredicate = cb.equal(root.get("movie"), movieId);
            Predicate datePredicate = cb.between(root.get("showTime"),
                    date.atStartOfDay(),
                    date.atStartOfDay().plusDays(1));
            return session.createQuery(criteriaQuery.where(moviePredicate, datePredicate))
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all MovieSessions", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.RoleName;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Override
    public void add(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            logger.info("Role was added to DB " + role.toString());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery("from Role "
                    + "where roleName = :roleName", Role.class);
            query.setParameter("roleName", RoleName.valueOf(roleName));
            return query.getSingleResult();
        }
    }
}

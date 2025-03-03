package org.myproject.core.data.daoimpl;

import org.hibernate.*;
import org.myproject.core.common.constant.CoreConstant;
import org.myproject.core.common.utils.HibernateUtil;
import org.myproject.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {

    private final Class<T> persistenceClass;

    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    // Convert ClassName to String
    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }


    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Transaction transaction = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            // HQL
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();

            // Success commit
            transaction.commit();
        } catch (HibernateException e) {
            // Fail
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return list;
    }

    public T update(T entity) {
        T result = null;
        Transaction transaction = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            // Merge return object
            Object object = session.merge(entity);
            result = (T) object;

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Transaction transaction = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            // Merge return object
            session.persist(entity);

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public T findById(ID id) {
        T result = null;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();

            // get(class, serializable) (persistenceClass, id)
            result = (T) session.get(persistenceClass, id);

            if (result == null) {
                throw new ObjectNotFoundException("NOT FOUND" + id, null);
            }
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return result;
    }

    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection) {
        List<T> list = new ArrayList<T>();
        Object totalItem = 0;

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();


        try {
            transaction = session.beginTransaction();

            StringBuilder sql = new StringBuilder("from ");
            sql.append(getPersistenceClassName());

            if (property != null && value != null) {
                sql.append(" where ").append(property).append("= :value");
            }
            if (sortDirection != null && sortExpression != null) {
                sql.append(" order by ").append(sortExpression);
                sql.append(" " + (sortDirection.equalsIgnoreCase(CoreConstant.SORT_ASC) ? "asc" : "desc"));
            }
            Query query1 = session.createQuery(sql.toString());
            if (value != null) {
                query1.setParameter("value", value);
            }
            list = query1.list();

            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersistenceClassName());
            if (property != null && value != null) {
                sql2.append("where ").append(property).append("= :value");
            }
            Query query2 = session.createQuery(sql2.toString());
            if (value != null) {
                query2.setParameter("value", value);
            }
            totalItem = query2.list().get(0);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();

            for (ID item : ids) {
                T t = (T) session.get(persistenceClass, item);
                session.delete(t);
                count++;
            }

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return count;
    }
}


package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.QyAProgramado;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class QyAProgramadoJpaController implements Serializable {

	public QyAProgramadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public QyAProgramadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QyAProgramado qyaProgramado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(qyaProgramado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QyAProgramado qyaProgramado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            qyaProgramado = em.merge(qyaProgramado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = qyaProgramado.getId();
                if (findQyAProgramado(id) == null) {
                    throw new NonexistentEntityException("The qyaProgramado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            QyAProgramado qyaProgramado;
            try {
                qyaProgramado = em.getReference(QyAProgramado.class, id);
                qyaProgramado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The qyaProgramado with id " + id + " no longer exists.", enfe);
            }
            em.remove(qyaProgramado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QyAProgramado> findQyAProgramadoEntities() {
        return findQyAProgramadoEntities(true, -1, -1);
    }

    public List<QyAProgramado> findQyAProgramadoEntities(int maxResults, int firstResult) {
        return findQyAProgramadoEntities(false, maxResults, firstResult);
    }

    private List<QyAProgramado> findQyAProgramadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QyAProgramado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public QyAProgramado findQyAProgramado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QyAProgramado.class, id);
        } finally {
            em.close();
        }
    }

    public int getQyAProgramadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QyAProgramado> rt = cq.from(QyAProgramado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

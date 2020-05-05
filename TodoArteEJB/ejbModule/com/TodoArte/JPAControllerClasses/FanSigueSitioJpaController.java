package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.FanSigueSitio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class FanSigueSitioJpaController implements Serializable {

	public FanSigueSitioJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public FanSigueSitioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FanSigueSitio fanSigueSitio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fanSigueSitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FanSigueSitio fanSigueSitio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            fanSigueSitio = em.merge(fanSigueSitio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = fanSigueSitio.getId();
                if (findFanSigueSitio(id) == null) {
                    throw new NonexistentEntityException("The fanSigueSitio with id " + id + " no longer exists.");
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
            FanSigueSitio fanSigueSitio;
            try {
                fanSigueSitio = em.getReference(FanSigueSitio.class, id);
                fanSigueSitio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fanSigueSitio with id " + id + " no longer exists.", enfe);
            }
            em.remove(fanSigueSitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FanSigueSitio> findFanSigueSitioEntities() {
        return findFanSigueSitioEntities(true, -1, -1);
    }

    public List<FanSigueSitio> findFanSigueSitioEntities(int maxResults, int firstResult) {
        return findFanSigueSitioEntities(false, maxResults, firstResult);
    }

    private List<FanSigueSitio> findFanSigueSitioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FanSigueSitio.class));
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

    public FanSigueSitio findFanSigueSitio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FanSigueSitio.class, id);
        } finally {
            em.close();
        }
    }

    public int getFanSigueSitioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FanSigueSitio> rt = cq.from(FanSigueSitio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

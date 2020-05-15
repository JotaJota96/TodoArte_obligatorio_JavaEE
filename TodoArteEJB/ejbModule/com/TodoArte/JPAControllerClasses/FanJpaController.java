package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.Fan;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class FanJpaController implements Serializable {

	public FanJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public FanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fan fan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fan fan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            fan = em.merge(fan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = fan.getNikname();
                if (findFan(id) == null) {
                    throw new NonexistentEntityException("The fan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fan fan;
            try {
                fan = em.getReference(Fan.class, id);
                fan.getNikname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fan with id " + id + " no longer exists.", enfe);
            }
            em.remove(fan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fan> findFanEntities() {
        return findFanEntities(true, -1, -1);
    }

    public List<Fan> findFanEntities(int maxResults, int firstResult) {
        return findFanEntities(false, maxResults, firstResult);
    }

    private List<Fan> findFanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fan.class));
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

    public Fan findFan(String id) {
        EntityManager em = getEntityManager();
        try {
        	// originalmente solo tenia la siguiente linea
            // return em.find(Fan.class, id);
        	// Yo lo cambie por:
        	
        	Query q;
        	q = em.createQuery("SELECT f FROM Fan f WHERE f.nikname = ?1 OR f.correo = ?2");
        	q.setParameter(1, id);
        	q.setParameter(2, id);
        	List<Fan> aux = (List<Fan>) q.getResultList();
        	
        	if (aux.size() == 1) {
        		return (Fan) aux.get(0);
        	}else {
        		return null;
        	}
        	
        } finally {
            em.close();
        }
    }

    public int getFanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fan> rt = cq.from(Fan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

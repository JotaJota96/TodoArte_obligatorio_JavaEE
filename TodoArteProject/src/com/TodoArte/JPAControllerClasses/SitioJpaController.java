package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.Sitio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SitioJpaController implements Serializable {

	public SitioJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public SitioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sitio sitio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sitio sitio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sitio = em.merge(sitio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = sitio.getId();
                if (findSitio(id) == null) {
                    throw new NonexistentEntityException("The sitio with id " + id + " no longer exists.");
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
            Sitio sitio;
            try {
                sitio = em.getReference(Sitio.class, id);
                sitio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sitio with id " + id + " no longer exists.", enfe);
            }
            em.remove(sitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sitio> findSitioEntities() {
        return findSitioEntities(true, -1, -1);
    }

    public List<Sitio> findSitioEntities(int maxResults, int firstResult) {
        return findSitioEntities(false, maxResults, firstResult);
    }

    private List<Sitio> findSitioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sitio.class));
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

    public Sitio findSitio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sitio.class, id);
        } finally {
            em.close();
        }
    }

    public int getSitioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sitio> rt = cq.from(Sitio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

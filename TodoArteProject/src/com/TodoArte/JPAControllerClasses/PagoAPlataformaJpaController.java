package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.PagoAPlataforma;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PagoAPlataformaJpaController implements Serializable {

	public PagoAPlataformaJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public PagoAPlataformaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagoAPlataforma pagoAPlataforma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pagoAPlataforma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagoAPlataforma pagoAPlataforma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pagoAPlataforma = em.merge(pagoAPlataforma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pagoAPlataforma.getId();
                if (findPagoAPlataforma(id) == null) {
                    throw new NonexistentEntityException("The pagoAPlataforma with id " + id + " no longer exists.");
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
            PagoAPlataforma pagoAPlataforma;
            try {
                pagoAPlataforma = em.getReference(PagoAPlataforma.class, id);
                pagoAPlataforma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoAPlataforma with id " + id + " no longer exists.", enfe);
            }
            em.remove(pagoAPlataforma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagoAPlataforma> findPagoAPlataformaEntities() {
        return findPagoAPlataformaEntities(true, -1, -1);
    }

    public List<PagoAPlataforma> findPagoAPlataformaEntities(int maxResults, int firstResult) {
        return findPagoAPlataformaEntities(false, maxResults, firstResult);
    }

    private List<PagoAPlataforma> findPagoAPlataformaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PagoAPlataforma.class));
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

    public PagoAPlataforma findPagoAPlataforma(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagoAPlataforma.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoAPlataformaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PagoAPlataforma> rt = cq.from(PagoAPlataforma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

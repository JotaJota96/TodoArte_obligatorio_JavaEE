package com.TodoArte.JPAControllerClasses;
/*
reemplazar
CategoriaSitio
por el nombre de la clase

reemplazar
categoriaSitio
por el nombre de la clase

*/
import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.CategoriaSitio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CategoriaSitioJpaController implements Serializable {

	public CategoriaSitioJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public CategoriaSitioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaSitio categoriaSitio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaSitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaSitio categoriaSitio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaSitio = em.merge(categoriaSitio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = categoriaSitio.getId();
                if (findCategoriaSitio(id) == null) {
                    throw new NonexistentEntityException("The categoriaSitio with id " + id + " no longer exists.");
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
            CategoriaSitio categoriaSitio;
            try {
                categoriaSitio = em.getReference(CategoriaSitio.class, id);
                categoriaSitio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaSitio with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaSitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaSitio> findCategoriaSitioEntities() {
        return findCategoriaSitioEntities(true, -1, -1);
    }

    public List<CategoriaSitio> findCategoriaSitioEntities(int maxResults, int firstResult) {
        return findCategoriaSitioEntities(false, maxResults, firstResult);
    }

    private List<CategoriaSitio> findCategoriaSitioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaSitio.class));
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

    public CategoriaSitio findCategoriaSitio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaSitio.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaSitioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaSitio> rt = cq.from(CategoriaSitio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.CategoriaContenido;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CategoriaContenidoJpaController implements Serializable {

	public CategoriaContenidoJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public CategoriaContenidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaContenido categoriaContenido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaContenido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaContenido categoriaContenido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaContenido = em.merge(categoriaContenido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = categoriaContenido.getId();
                if (findCategoriaContenido(id) == null) {
                    throw new NonexistentEntityException("The categoriaContenido with id " + id + " no longer exists.");
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
            CategoriaContenido categoriaContenido;
            try {
                categoriaContenido = em.getReference(CategoriaContenido.class, id);
                categoriaContenido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaContenido with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaContenido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaContenido> findCategoriaContenidoEntities() {
        return findCategoriaContenidoEntities(true, -1, -1);
    }

    public List<CategoriaContenido> findCategoriaContenidoEntities(int maxResults, int firstResult) {
        return findCategoriaContenidoEntities(false, maxResults, firstResult);
    }

	private List<CategoriaContenido> findCategoriaContenidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaContenido.class));
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

    public CategoriaContenido findCategoriaContenido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaContenido.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaContenidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaContenido> rt = cq.from(CategoriaContenido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

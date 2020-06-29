package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;

import com.TodoArte.Classes.NotificacionArtista;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class NotificacionArtistaJpaController implements Serializable {

	public NotificacionArtistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public NotificacionArtistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotificacionArtista notificacionArtista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notificacionArtista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotificacionArtista notificacionArtista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notificacionArtista = em.merge(notificacionArtista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = notificacionArtista.getId();
                if (findNotificacionArtista(id) == null) {
                    throw new NonexistentEntityException("The notificacionArtista with id " + id + " no longer exists.");
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
            NotificacionArtista notificacionArtista;
            try {
                notificacionArtista = em.getReference(NotificacionArtista.class, id);
                notificacionArtista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificacionArtista with id " + id + " no longer exists.", enfe);
            }
            em.remove(notificacionArtista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotificacionArtista> findNotificacionArtistaEntities() {
        return findNotificacionArtistaEntities(true, -1, -1);
    }

    public List<NotificacionArtista> findNotificacionArtistaEntities(int maxResults, int firstResult) {
        return findNotificacionArtistaEntities(false, maxResults, firstResult);
    }

    private List<NotificacionArtista> findNotificacionArtistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotificacionArtista.class));
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

    public NotificacionArtista findNotificacionArtista(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotificacionArtista.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionArtistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotificacionArtista> rt = cq.from(NotificacionArtista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

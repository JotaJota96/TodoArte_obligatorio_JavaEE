package com.TodoArte.JPAControllerClasses;

import com.TodoArte.JPAControllerClasses.exceptions.NonexistentEntityException;
import com.TodoArte.Classes.Artista;
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

public class ArtistaJpaController implements Serializable {

	public ArtistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PersistenceUnitName.getPersistenceUnitName());
    }
    
    public ArtistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artista artista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(artista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void create(Artista artista, Sitio sitio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(artista);
            em.persist(sitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void edit(Artista artista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            artista = em.merge(artista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = artista.getNikname();
                if (findArtista(id) == null) {
                    throw new NonexistentEntityException("The artista with id " + id + " no longer exists.");
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
            Artista artista;
            try {
                artista = em.getReference(Artista.class, id);
                artista.getNikname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artista with id " + id + " no longer exists.", enfe);
            }
            em.remove(artista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artista> findArtistaEntities() {
        return findArtistaEntities(true, -1, -1);
    }

    public List<Artista> findArtistaEntities(int maxResults, int firstResult) {
        return findArtistaEntities(false, maxResults, firstResult);
    }

    private List<Artista> findArtistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artista.class));
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

    public Artista findArtista(String id) {
        EntityManager em = getEntityManager();
        try {
        	// originalmente solo tenia la siguiente linea
            // return em.find(Artista.class, id);
        	// Yo lo cambie por:

        	Query q;
        	q = em.createQuery("SELECT f FROM Artista f WHERE f.nikname = ?1 OR f.correo = ?2");
        	q.setParameter(1, id);
        	q.setParameter(2, id);
        	List<Artista> aux = (List<Artista>) q.getResultList();
        	
        	if (aux.size() == 1) {
        		return (Artista) aux.get(0);
        	}else {
        		return null;
        	}
        } finally {
            em.close();
        }
    }

    public int getArtistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artista> rt = cq.from(Artista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

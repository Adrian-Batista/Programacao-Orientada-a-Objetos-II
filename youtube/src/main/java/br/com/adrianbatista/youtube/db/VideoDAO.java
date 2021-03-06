package br.com.adrianbatista.youtube.db;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import br.com.adrianbatista.youtube.entities.User;
import br.com.adrianbatista.youtube.entities.Video;

public class VideoDAO implements InterfaceDAO<Video>{

	@Override
	public void persist(Video t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		}catch(EntityExistsException e){
			em.getTransaction().rollback();
			Video original = get(t.getName());
			em.getTransaction().begin();
			original.setDescription(t.getDescription());
			original.setLike(t.getLike());
			em.getTransaction().commit();
			
		}
		
	}

	@Override
	public void remove(Video t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}
	
	public Video get(Object pk) {
		return UtilDB.getEntityManager().find(Video.class, pk);
	}
	
	@Override
	public List<Video> getAll() {
		return UtilDB.getEntityManager().createQuery("SELECT + u FROM User u", Video.class).getResultList();
	}

}

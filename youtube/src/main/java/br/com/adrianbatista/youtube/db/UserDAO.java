package br.com.adrianbatista.youtube.db;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.adrianbatista.youtube.entities.User;

public class UserDAO implements InterfaceDAO<User>{

	@Override
	public void persist(User t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public void remove(User t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}
	
	public User get(Object pk) {
		EntityManager em = UtilDB.getEntityManager();
		User t = em.find(User.class, pk);
		return t;
	}
	
	@Override
	public List<User> getAll() {
		EntityManager em = UtilDB.getEntityManager();
		List<User> users = em.createNativeQuery("SELECT + FROM User").getResultList();
		return users;
	}

}

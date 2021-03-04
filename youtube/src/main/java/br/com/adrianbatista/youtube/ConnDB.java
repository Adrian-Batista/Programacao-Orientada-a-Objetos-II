package br.com.adrianbatista.youtube;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnDB {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		if(entityManager == null)
			entityManager = getEntityManagerFactory().createEntityManager();
		return entityManager;
	}
	
	private static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("teste");
		return entityManagerFactory;
	}
	
	public static void closeConn() {
		if(entityManager != null)
			entityManager.close();
		if(entityManagerFactory != null)
			entityManagerFactory.close();
	}

}

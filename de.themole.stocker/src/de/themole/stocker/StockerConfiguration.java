package de.themole.stocker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import lombok.Data;

@Data
public class StockerConfiguration {
	
	private static StockerConfiguration instance = null;
	
	private EntityManagerFactory entityManagerFactory = null;
	
	protected StockerConfiguration() {
		entityManagerFactory = Persistence.createEntityManagerFactory("pu_stocker");
	}
	
	public static StockerConfiguration getInstance() {
		if (instance == null) {
			instance = new StockerConfiguration();
		}
		return instance;
	}
	
	public static EntityManager getEntitiyManager() {
		return getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public static CriteriaBuilder getCriteriaBuilder() {
		return getInstance().getEntityManagerFactory().getCriteriaBuilder();
	}
	
	public static Metamodel getMetamodel() {
		return getInstance().getEntityManagerFactory().getMetamodel();
	}
	
	public static PersistenceUnitUtil getPersistenceUnitUtil() {
		return getInstance().getEntityManagerFactory().getPersistenceUnitUtil();
	}
}

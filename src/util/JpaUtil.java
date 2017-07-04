package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	//variavel estatica
	private static EntityManagerFactory managerFactory;
	
	//instancia estatica de Persistence
	static {
		managerFactory = Persistence.createEntityManagerFactory("CarroPu");
	}
	
	//getter
	public static EntityManager getManagerFactory() {
		return managerFactory.createEntityManager();
	}	
	
	//fecha	
	public static void closeEntityManager(){
		managerFactory.close();
	}
}

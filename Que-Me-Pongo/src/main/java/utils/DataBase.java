package utils;
import javax.persistence.*;

public class DataBase {
	
static EntityManagerFactory entity = Persistence.createEntityManagerFactory("db");
static EntityManager  em = entity.createEntityManager();
static EntityTransaction et = em.getTransaction();

public static EntityManager getSession() {
return em;
}

public static void beginTran()
{
et.begin();
}

public static void rollbackTran()
{
et.rollback();
}

public static void commitTran()
{
et.commit();
}
}

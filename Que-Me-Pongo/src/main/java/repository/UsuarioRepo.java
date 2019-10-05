package repository;

import java.util.List;

import javax.mail.Session;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.clases.Usuario;
import utils.DataBase;
import utils.Utils;

public class UsuarioRepo implements Repository<Usuario>{

	@Override
	public List<Usuario> all() {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario getUserById(long id)
	{
	return DataBase.getSession().find(Usuario.class,id);
	}
	
	public Usuario getUser(String userName,String userPassword)
	{
	Usuario user = null;
	try
	{
	String query = "SELECT u FROM Usuario u"
			     + " WHERE user = :nomUsuario"
			     + " and password = :pass ";
	Query userQuery = DataBase.getSession().
			          createQuery(query,Usuario.class).
	                  setParameter("nomUsuario",userName).
	                  setParameter("pass",Utils.generarHash256(userPassword)).
	                  setMaxResults(1);
	                  
	user = (Usuario) userQuery.getSingleResult();
	}
	catch(NoResultException e)
	{
	System.out.print(e.toString());
	}
	return user;
	}
}

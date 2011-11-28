package org.game.dao;

import java.util.List;

import org.game.dao.base.*;
import org.game.model.User;

public interface UserDao extends BaseDao{
	/**
	 * ??????????
	 * @param name ???
	 * @param pass ??
	 * @return ???List
	 */
	List findByNameAndPass(String name , String pass);
	
	/**
	 * ???????
	 * @return ???????
	 */
	
	List findAllUser();
	
	/**
	 * ???????????
	 * @param user ???
	 */
	User findByName(String user);
	

	
}

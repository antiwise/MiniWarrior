package org.game.dao.base;

import java.util.List;



public interface BaseDao {
	
	/*
	 * get Object
	 */
	Object get(Integer id);
	
	/*
	 * save Object
	 */
	void save(Object obj);
	
	/*
	 * update Object
	 */
	void update(Object obj);
	
	/*
	 * by id delete Object
	 */
	void delete( Integer id );
	/*
	 * delete Object
	 */
	void delete(Object obj);
	
	/*
	 * @return Object List
	 */
	List findAll();
}

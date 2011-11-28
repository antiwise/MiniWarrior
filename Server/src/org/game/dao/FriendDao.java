package org.game.dao;

import java.util.List;

import org.game.dao.base.*;

public interface FriendDao extends BaseDao{
	/**
	 * finder firend by userNameId
	 * @param id userNameId
	 * @return List for friendName
	 */
	List findFriend(String user);
}

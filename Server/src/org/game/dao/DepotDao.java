package org.game.dao;

import org.game.dao.base.BaseDao;
import java.util.List;

public interface DepotDao extends BaseDao{
	List viewDepot(String user);
}

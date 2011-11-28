package org.game.dao;

import org.game.dao.base.BaseDao;
import org.game.model.GoodsItem;

public interface GoodsItemDao extends BaseDao{
	GoodsItem findByName(String name );
	
}

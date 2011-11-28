package org.game.service.impl;

import org.game.exception.MyException;
import org.game.service.*;
import org.game.vo.UserVo;
import org.game.dao.*;
import org.game.model.*;

import java.util.List;


public class MyServiceImpl implements MyService{
	
	private DepotDao depotDao;
	private FriendDao friendDao;
	private GoodsDao goodsDao;
	private GoodsItemDao goodsItemDao;
	private SculptDao sculptDao;
	private ShopDao shopDao;
	private ShopGoodsDao shopGoodsDao;
	private UserDao userDao;
	private UserGoodsDao userGoodsDao;

	public void setDepotDao(DepotDao depotDao)
	{
		this.depotDao = depotDao;
	}
	public void setFriendDao(FriendDao friendDao)
	{
		this.friendDao = friendDao;
	}
	public void setGoodsDao(GoodsDao goodsDao)
	{
		this.goodsDao = goodsDao;
	}
	public void setGoodsItemDao(GoodsItemDao goodsItemDao)
	{
		this.goodsItemDao = goodsItemDao;
	}
	public void setSculptDao(SculptDao sculptDao)
	{
		this.sculptDao = sculptDao;
	}
	public void setShopDao(ShopDao shopDao)
	{
		this.shopDao = shopDao;
	}
	public void setShopGoodsDao(ShopGoodsDao shopGoodsDao)
	{
		this.shopGoodsDao = shopGoodsDao;
	}	
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
	public void setUserGoodsDao(UserGoodsDao userGoodsDao)
	{
		this.userGoodsDao = userGoodsDao;
	}

	public int validLogin(UserVo userVo) throws MyException
	{
		try 
		{
			List list = userDao.findByNameAndPass(userVo.getName(),userVo.getPass());
			if ( list.size()>=1 )
			{
				User user = (User)list.get(0);
				int purview = user.getUserLevel();
				if (purview == LOGIN_ADMIN )
				{
					return LOGIN_ADMIN;
				}
				return LOGIN_USER;
			}
			return LOGIN_FAIL;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===validLogin===");
		}
	}
	public List viewUser() throws MyException
	{
		try
		{
			return userDao.findAllUser();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===viewUser===");
		}
	}
    public List viewShop()  throws MyException
    {
		try
		{
			return shopDao.findAll();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===viewShop===");
		}
    }
    /*
     * viewDepot
     */
    public List viewDepot(String user) throws MyException
    {
		try
		{
			return depotDao.viewDepot(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===viewShop===");
		}
    }

   /**
    * 
    */
    public User viewMyInfo(String user) throws MyException
    {
		try
		{
			return userDao.findByName(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===viewMyInfo===");
		}
    }
    public List viewFirend(String user) throws MyException
    {
    	try 
    	{
    		return friendDao.findFriend(user);
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===viewFirend===");	
    	}
    }
    public List viewGoodsItem() throws MyException
    {
    	try
    	{
    		return goodsItemDao.findAll();
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===viewGameGoodsItem===");	
    	}
    }
    public List viewSculpt() throws MyException
    {
    	try
    	{
    		return sculptDao.findAll();
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===viewSculpt===");	
    	}
    }
        
    public void addUser(String name , String pass ,String sex , 
    		String email,String address,String locality,Integer sculpt) throws MyException
    {
    	try
    	{
    		User user = new User(1,name,pass,sex,email,address,locality,1,1,1,sculpt.intValue(),1);
    		user.setSculpt( ((Sculpt)sculptDao.get(new Integer(1))) );
    		userDao.save(user);
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===addUser===");	
    	}
    }
    public void addGoodsItem(String name) throws MyException
    {
    	try
    	{
    		goodsItemDao.save( new GoodsItem(name) );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===addGoodsItem===");	
    	}
    }
    public   void addShop(String name) throws MyException
    {
    	try
    	{
    		shopDao.save( new Shop(name) );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===addShop===");	
    	}   	
    }
    public GoodsItem findGoodsItem(String name) throws MyException
    {
    	try
    	{
    		System.out.println(name+"==serv");
    		return goodsItemDao.findByName( name );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===findGoodsItem===");	
    	}
    }
    public void delUser(Integer userId)throws MyException
    {
    	try
    	{
    		userDao.delete( userId );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===delUser===");	
    	}
    }
    public void delShop(Integer shopId)throws MyException
    {
    	
    	try
    	{
    		shopDao.delete( shopId );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===delShop===");	
    	} 	
    }
    
    public  void delGoodsItem(Integer goodsItemId)throws MyException
    {
    	try
    	{
    		goodsItemDao.delete( goodsItemId );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===delGoodsItem===");	
    	}	
    }
    public User findUser(Integer userId)throws MyException
    {
    	try
    	{
    		return (User)userDao.get( userId );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===findUser===");	
    	}
    }
    public Shop findShop(Integer shopId)throws MyException
    {
    	try
    	{
    		return (Shop)shopDao.get( shopId );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===findShop===");	
    	}   	
    }
    public GoodsItem findGoodsItem(Integer goodsItemId)throws MyException
    {
    	try
    	{
    		return (GoodsItem)goodsItemDao.get( goodsItemId );
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===findGoodsItem===");	
    	}   	
    }
    public void modifyUser(Integer userId,String name,String pass,String sex,String email,String address,String locality,Integer sculpt) throws MyException
    {
    	try
    	{
    		User user = (User)userDao.get(userId);
    		Sculpt suclptObj = (Sculpt)sculptDao.get(sculpt);
    		//user.setUserName(name);
    		user.setUserPass(pass);
    		user.setUserSex(sex);
    		user.setUserMail(email);
    		user.setUserAddress(address);
    		user.setUserLocality(locality);
    		user.setSculpt(suclptObj);
    		System.out.print("dd");
    		userDao.update(user);
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			throw new MyException("===Exception===MyServiceImpl===modifyUser===");	
    	}
    }
    public void modifyShop(Integer shopId,String shopName)throws MyException
    {
    	Shop shop = (Shop)shopDao.get(shopId);
    	shop.setShopName(shopName);
		userDao.update(shop);
    }
    public void modifyGoodsItem(Integer goodsItemId,String giName)throws MyException
    {
    	GoodsItem gi = (GoodsItem)goodsItemDao.get(goodsItemId);
    	gi.setGoodsItemName(giName);
    	goodsItemDao.update(gi);
    }
}
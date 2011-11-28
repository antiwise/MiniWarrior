package org.game.service;


import org.game.exception.MyException;
import org.game.vo.UserVo;
import org.game.model.*;


import java.util.List;

public interface MyService {

    public static final int LOGIN_FAIL = 0;

    public static final int LOGIN_ADMIN = 2;

    public static final int  LOGIN_USER = 1;
    
    int validLogin(UserVo userVo) throws MyException;
    
    List viewUser()  throws MyException;
    
    List viewShop()  throws MyException;
    
    User viewMyInfo(String user)  throws MyException;
    
    List viewFirend(String user) throws MyException;
    
    List viewDepot(String user) throws MyException;
    
    List viewGoodsItem() throws MyException;
    
    List viewSculpt() throws MyException;
    
    void addUser(String name , String pass ,
    		String sex , String email,String address,
    		String locality,Integer sculpt) throws MyException;
  
    void addGoodsItem(String name) throws MyException;
    
    GoodsItem findGoodsItem(String name) throws MyException;
    
    void delUser(Integer userId)throws MyException;
    
    void addShop(String name) throws MyException;
    
    void delGoodsItem(Integer goodsItemId)throws MyException;
    
    void delShop(Integer shopId)throws MyException;
    
    User findUser(Integer userId)throws MyException;
    
    Shop findShop(Integer shopId)throws MyException;
    
    void modifyUser(Integer userId,String name,String pass,String sex,
    		String email,String address,String locality,Integer sculpt) throws MyException;
    
    void modifyShop(Integer shopId,String shopName)throws MyException;
    
    GoodsItem findGoodsItem(Integer goodsItemId)throws MyException;
    
    void modifyGoodsItem(Integer goodsItemId,String giName)throws MyException;
    
}

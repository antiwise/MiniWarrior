/**
 * @(#)User.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;

import java.util.Set;
import java.util.HashSet;

public class User
{
	private int userId;
	private String userName;
	private String userPass;
	private String userSex;
	private String userMail;
	private String userAddress;
	private String userLocality;
	private int userMoney;
	private int userExpe;	
	private int userBlood;
	private int userAttack;
	private int userLevel;
	private Sculpt sculpt;
	private Set<UserGoods> userGoods = new HashSet<UserGoods>(); 
	private Set<Depot> depots = new HashSet<Depot>(); 
	private Set<Friend> friends = new HashSet<Friend>(); 

	public User()
	{
	}


	public User(int userId, String userName, String userPass, String userSex, 
				String userMail, String userAddress, String userLocality, int userMoney, 
				int userExpe, int userBlood, int userAttack, int userLevel) {
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.userSex = userSex;
		this.userMail = userMail;
		this.userAddress = userAddress;
		this.userLocality = userLocality;
		this.userMoney = userMoney;
		this.userExpe = userExpe;
		this.userBlood = userBlood;
		this.userAttack = userAttack;
		this.userLevel = userLevel;

	}
	
	public void setUserId(int userId) {
		this.userId = userId; 
	}

	public void setUserName(String userName) {
		this.userName = userName; 
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass; 
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex; 
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail; 
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress; 
	}

	public void setUserLocality(String userLocality) {
		this.userLocality = userLocality; 
	}

	public void setUserMoney(int userMoney) {
		this.userMoney = userMoney; 
	}

	public void setUserExpe(int userExpe) {
		this.userExpe = userExpe; 
	}

	public void setUserBlood(int userBlood) {
		this.userBlood = userBlood; 
	}

	public void setUserAttack(int userAttack) {
		this.userAttack = userAttack; 
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel; 
	}

	public void setSculpt(Sculpt sculpt) {
		this.sculpt = sculpt; 
	}
	
	public void setUserGoods(Set<UserGoods> userGoods) {
		this.userGoods = userGoods; 
	}

	public void setDepots(Set<Depot> depots) {
		this.depots = depots; 
	}
	
	public void setFriends(Set<Friend> friends) {
		this.friends = friends; 
	}


	public int getUserId() {
		return (this.userId); 
	}

	public String getUserName() {
		return (this.userName); 
	}

	public String getUserPass() {
		return (this.userPass); 
	}

	public String getUserSex() {
		return (this.userSex); 
	}

	public String getUserMail() {
		return (this.userMail); 
	}

	public String getUserAddress() {
		return (this.userAddress); 
	}

	public String getUserLocality() {
		return (this.userLocality); 
	}

	public int getUserMoney() {
		return (this.userMoney); 
	}

	public int getUserExpe() {
		return (this.userExpe); 
	}

	public int getUserBlood() {
		return (this.userBlood); 
	}

	public int getUserAttack() {
		return (this.userAttack); 
	}

	public int getUserLevel() {
		return (this.userLevel); 
	}

	public Sculpt getSculpt() {
		return (this.sculpt); 
	}

	public Set<UserGoods> getUserGoods() {
		return (this.userGoods); 
	}

	public Set<Depot> getDepots() {
		return (this.depots); 
	}
	public Set<Friend> getFriends() {
		return (this.friends); 
	}
	
}

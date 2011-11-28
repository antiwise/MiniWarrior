/**
 * @(#)StartServer.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-21
 */
package org.game.server;

import org.blue.global.Application;
import org.blue.net.Server;

import org.game.blue.role.people.NpcEvents;
import org.game.blue.role.people.PersonAction;
import org.game.blue.role.people.PersonRequest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;

public class StartServer 
{
	public static void main(String[] args)
	{
		Application.regeditClass("PersonRequest",PersonRequest.class);
		Application.regeditClass("PersonAction",PersonAction.class);
		Application.regeditClass("NpcEvents",NpcEvents.class);
		Application.addStartEndListenter(new ResponseStartEnd());
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("applicationContext.xml");
		Server ser = new Server();
		ser.startServer();
	}
}
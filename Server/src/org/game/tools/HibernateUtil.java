/**
 * @(#)HibernateUtil.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-5-5
 */
package org.game.tools;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil 
{
	public static final SessionFactory sessionFactory;

    static
	{
        try
		{
            //����Ĭ�ϵ�hibernate.cfg.xml������һ��Configuration��ʵ��
			Configuration configuration=new Configuration().configure();
			//��Configuration��ʵ��������һ��SessionFactoryʵ��
            sessionFactory = configuration.buildSessionFactory();
        }
		catch (Throwable ex)
		{
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //ThreadLocal�������̱߳��ػ���ʵ��,�����ֲ߳̾�������Ҳ����˵ÿ��ʹ�øñ������̶߳�����Ϊ
	//�ñ����ṩһ������,ÿ���̸߳ı�ñ�����ֵ�����Ǹı�ø�����ֵ,������Ӱ�������̵߳ĸñ���
	//��ֵ.

	//ThreadLocal�Ǹ������̵߳����ݹ��������ڶ���߳�֮�乲����Դ,��˲�����Ҫ���߳�ͬ��    
	public static final ThreadLocal session = new ThreadLocal();

    public static Session currentSession() throws HibernateException
	{
        Session s = (Session) session.get();
        //������̻߳�û��Session,�򴴽�һ���µ�Session
        if (s == null)
		{
            s = sessionFactory.openSession();
            //����õ�Session�����洢��ThreadLocal����session��
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s != null)
            s.close();
        session.set(null);
    }
}

##事务
1.什么是事务
	事务(Transaction)是并发控制的基本单位。所谓事务,它是一个操作序列，这些操作要么都执行，要么都不执行，它是一个不可分割的工作单位。例如，银行转帐工作：从一个帐号扣款并使另一个帐号增款，这两个操作要么都执行，要么都不执行。所以，应该把他们看成一个事务。事务是数据库维护数据一致性的单位，在每个事务结束时，都能保持数据一致性。
	
2.事务应该加在springmvc中的哪一层
	service层。从直观角度来看，事务似乎应该加在dao层，直接控制数据库，但是加入两个表有关联的时候，比如登陆的时候有一个user表和user_log表，登陆不仅需要查询user表，还需要在user_log表添加一条记录，此时如果将事务加在dao层，则处理user表的和处理user_log表的不是同一个事务，当一个表的操作抛出异常时，无法另另一个表的事务回滚。所以应该加在service层。
	
3.spring中事务是如何实现的
	采用了aop的方式。之前在会议上说错了，spring的aop确实是基于动态代理实现的。而spring中的事务管理则是交给aop去处理。
	
##HibernateTemplate
HibernateTemplate是一个封装类，用到了模板方法设计模式。使用HibernateTemplate时，不显示的声明事务管理，照样能对数据库做CURD。这是因为在没有声明事务时，HibernateTemplate是默认自动提交事务的。因此如果在业务代码中，只涉及到一次数据库操作时默认自动事务提交已经足够，如果多次数据库操作则需要做事务声明。当你在spring配置了事务的时候,hibernateTemplate就会用当前spring配置的事务,如果spring没有配置事务,hibernateTemplate又是必须要一个事务,则它会自己创建一个事务开始并提交!!
HibernateTemplate利用回调的方式在你自己写的hql语句前后实现事务的处理，封装session。

至于回调，有一个比较经典的回调方式：
Class A实现接口CallBack callback——背景1
class A中包含一个class B的引用b ——背景2
class B有一个参数为callback的方法f(CallBack callback) ——背景3
A的对象a调用B的方法 f(CallBack callback) ——A类调用B类的某个方法 C
然后b就可以在f(CallBack callback)方法中调用A的方法 ——B类调用A类的某个方法D

HibernateTemplate具体内部流程如下（此为HibernateTemplate源码摘录）：

	public interface HibernateCallback<T> {//回调函数接口
	T doInHibernate(Session session) throws HibernateException;
	}

	@Override
	public Serializable save(final String entityName, final Object entity) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<Serializable>() {//用匿名类实现情景1
			@Override
			public Serializable doInHibernate(Session session) throws HibernateException {//session为引用，背景2
				checkWriteOperationAllowed(session);
				return session.save(entityName, entity);
			}
		});
	}
	
	public <T> T executeWithNativeSession(HibernateCallback<T> action) {
		return doExecute(action, true);
	}

	protected <T> T doExecute(HibernateCallback<T> action, boolean enforceNativeSession) throws 	 DataAccessException {//情景3
		Assert.notNull(action, "Callback object must not be null");

		Session session = null;
		boolean isNew = false;
		try {
			session = getSessionFactory().getCurrentSession();
		}
		catch (HibernateException ex) {
			logger.debug("Could not retrieve pre-bound Hibernate session", ex);
		}
		if (session == null) {
			session = getSessionFactory().openSession();
			session.setFlushMode(FlushMode.MANUAL);
			isNew = true;
		}

		try {
			enableFilters(session);
			Session sessionToExpose =
					(enforceNativeSession || isExposeNativeSession() ? session : createSessionProxy(session));
			return action.doInHibernate(sessionToExpose);
		}
		catch (HibernateException ex) {
			throw SessionFactoryUtils.convertHibernateAccessException(ex);
		}
		catch (RuntimeException ex) {
			// Callback code threw application exception...
			throw ex;
		}
		finally {
			if (isNew) {
				SessionFactoryUtils.closeSession(session);
			}
			else {
				disableFilters(session);
			}
		}
	}
	
	
	




	
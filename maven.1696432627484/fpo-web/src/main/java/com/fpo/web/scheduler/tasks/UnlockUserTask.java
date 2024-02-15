package com.fpo.web.scheduler.tasks;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UnlockUserTask {

	final static Logger logger = Logger.getLogger(UnlockUserTask.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Scheduled(fixedRate = 60000)
	public void unlockLockedUsers()
	{
		try
		{
			String sql = "UPDATE t_umt_user set is_locked = false , wrong_login_cnt = 0, is_logged_in = false where is_locked = true ";
			Query qry = em.createNativeQuery(sql);
			
			qry.executeUpdate();
			//logger.debug("Ran unlockLockedUsers");
		}
		catch(Exception ex)
		{
			logger.error(ex);
		}
	}
}

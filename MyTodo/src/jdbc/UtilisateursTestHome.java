package jdbc;

// Generated 21 févr. 2014 11:09:10 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class UtilisateursTest.
 * @see jdbc.UtilisateursTest
 * @author Hibernate Tools
 */
public class UtilisateursTestHome {

	private static final Log log = LogFactory
			.getLog(UtilisateursTestHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(UtilisateursTest transientInstance) {
		log.debug("persisting UtilisateursTest instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UtilisateursTest instance) {
		log.debug("attaching dirty UtilisateursTest instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UtilisateursTest instance) {
		log.debug("attaching clean UtilisateursTest instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UtilisateursTest persistentInstance) {
		log.debug("deleting UtilisateursTest instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UtilisateursTest merge(UtilisateursTest detachedInstance) {
		log.debug("merging UtilisateursTest instance");
		try {
			UtilisateursTest result = (UtilisateursTest) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UtilisateursTest findById(java.lang.Integer id) {
		log.debug("getting UtilisateursTest instance with id: " + id);
		try {
			UtilisateursTest instance = (UtilisateursTest) sessionFactory
					.getCurrentSession().get("jdbc.UtilisateursTest", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UtilisateursTest instance) {
		log.debug("finding UtilisateursTest instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("jdbc.UtilisateursTest")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

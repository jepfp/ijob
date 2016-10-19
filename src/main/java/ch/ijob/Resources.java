package ch.ijob;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {
    
    private static final String PERSISTENCE_UNIT_NAME = "ijob-persistence-unit";
    
	@Produces
	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager em;

}

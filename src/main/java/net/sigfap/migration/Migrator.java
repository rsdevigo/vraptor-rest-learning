package net.sigfap.migration;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.events.VRaptorInitialized;

public class Migrator {
	
	@Inject
	Logger logger;
	
	@Inject
	Environment environment;
	
	public void migrate(@Observes VRaptorInitialized evento) {
		
		logger.debug("migrando banco de dados...");
		
		String url = environment.get("database.url");
		String username = environment.get("database.user");
		String password = environment.get("database.password");
		
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, username, password);
        flyway.migrate();
	}
}
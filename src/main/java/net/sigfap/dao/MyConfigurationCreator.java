package net.sigfap.dao;

import java.net.URL;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.hibernate.ConfigurationCreator;

@Specializes
public class MyConfigurationCreator extends ConfigurationCreator {
	
	Environment environment;
	
	@Inject
	public MyConfigurationCreator(Environment environment) {
		this.environment = environment;
	}
	
	@Produces
	@ApplicationScoped
	public Configuration getInstance() {
		
		URL location = environment.getResource("/hibernate.cfg.xml");
		
		Configuration cfg = new Configuration().configure(location);
		
		cfg.setProperty("hibernate.connection.url", environment.get("database.url"));
		cfg.setProperty("hibernate.connection.username", environment.get("database.user"));
		cfg.setProperty("hibernate.connection.password", environment.get("database.password"));
		
		return cfg;
	}
}

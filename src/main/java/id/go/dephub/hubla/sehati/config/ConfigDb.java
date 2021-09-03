package id.go.dephub.hubla.sehati.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfigDb {
	
	@Bean(name = "portalDb")
	@ConfigurationProperties(prefix = "spring.dbportal")
	public DataSource portalDataSource() { 
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "portalJdbcTemplate")
	public JdbcTemplate portalJdbcTemplate(@Qualifier("portalDb") 
                                              DataSource dsPostgres) {
		return new JdbcTemplate(dsPostgres);
	}
	
	@Bean(name = "workflowDb")
	@ConfigurationProperties(prefix = "spring.dbworkflow")
	public DataSource workflowDataSource() { 
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "workflowJdbcTemplate")
	public JdbcTemplate workflowJdbcTemplate(@Qualifier("workflowDb") 
                                              DataSource dsPostgres) {
		return new JdbcTemplate(dsPostgres);
	}
	
	@Bean(name = "datalakeDb")
	@ConfigurationProperties(prefix = "spring.dbdatalake")
	public DataSource datalakeDataSource() {
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "datalakeJdbcTemplate")
	public JdbcTemplate datalakeJdbcTemplate(@Qualifier("datalakeDb") 
                                              DataSource dsPostgres) {
		return new JdbcTemplate(dsPostgres);
	}
	
	@Bean(name = "datalakeOldDb")
	@ConfigurationProperties(prefix = "spring.dbdatalakeold")
	public DataSource datalakeOldDataSource() {
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "datalakeOldJdbcTemplate")
	public JdbcTemplate datalakeOldJdbcTemplate(@Qualifier("datalakeOldDb") 
                                              DataSource dsPostgres) {
		return new JdbcTemplate(dsPostgres);
	}
}

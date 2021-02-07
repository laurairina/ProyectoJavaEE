package config;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager; 
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration  //es hija de @component, esta la clase que contiene declaraciones de beans
@EnableTransactionManagement  //Habilita el uso de @transactional 
public class JpaConfig {
	final static Logger milogger = Logger.getLogger(JpaConfig .class);
	
	//el nombre del metodo es el id del bean
	@Bean //define un bean, cuyo id es el nombre del metodo  --> entityManagerFactory
	public EntityManagerFactory entityManagerFactory() {
		milogger.info("Entra en el jpaConfig de entityManagerFactory");
		//Aqui estamos creando el EntityManagerFatory
		return Persistence.createEntityManagerFactory("ProyectoBlog"); //nombre igual a unidad de  persistence.xml
	}
	
	@Bean
	public EntityManager entityManager() {
		milogger.info("Entra en el jpaConfig de entityManager");
		//Aqui estamos creando el EntityManager
		return entityManagerFactory().createEntityManager();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		milogger.info("Entra en el jpaConfig de transactionManager");
		//Aqui se esta creando la transaccion
		return new JpaTransactionManager(entityManagerFactory());
	}

	
	
}

package com.backend.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
//BD en caso que se haga en linea mas a futuro:
/*#spring.datasource.url=jdbc:mysql://bd-proyecto-aws.cbdhz1ksbfwl.us-east-1.rds.amazonaws.com:3306/notasdb_aws?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true
#spring.datasource.url=jdbc:mysql://bd-proyecto-aws.cbdhz1ksbfwl.us-east-1.rds.amazonaws.com:3306/notasdb_aws?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.url=jdbc:mysql://bd-proyecto-aws.cbdhz1ksbfwl.us-east-1.rds.amazonaws.com:3306/notasdb_aws
#spring.datasource.url=jdbc:mysql://localhost:3306/notasdb_aws
spring.datasource.username=root
spring.datasource.password=G#dx6Q&Gig5X3PX4

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
spring.jpa.hibernate.ddl-auto=update
logging.level.org.hibernate.SQL=debug
spring.datasource.hikari.maxLifetime=600000*/

		
	}

}

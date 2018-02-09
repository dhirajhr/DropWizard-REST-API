package com.zappos.restaurant;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;


import com.zappos.restaurant.TemplateHealthCheck;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;


import com.zappos.restaurant.HelloWorldConfiguration;
import com.zappos.restaurant.TemplateHealthCheck;
import com.zappos.restaurant.PartsResource;
import com.zappos.restaurant.PartsService;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import java.util.EnumSet;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	
	private static final String SQL = "sql";
	  private static final String DROPWIZARD_BLOG_SERVICE = "Dropwizard blog service";
	  private static final String BEARER = "Bearer";

	
	
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }


    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
    	
    	
    	//enable cors
    	  final FilterRegistration.Dynamic cors =
    		        environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    		    // Configure CORS parameters
    		    cors.setInitParameter("allowedOrigins", "*");
    		    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    		    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

    		    // Add URL mapping
    		    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	
    	
        // Datasource configuration
        final DataSource dataSource =
            configuration.getDataSourceFactory().build(environment.metrics(), SQL);
        DBI dbi = new DBI(dataSource);

        // Register Health Check
        TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(dbi.onDemand(PartsService.class));
        environment.healthChecks().register(DROPWIZARD_BLOG_SERVICE, healthCheck);

        // Register OAuth authentication
     /*   environment.jersey()
            .register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new DropwizardBlogAuthenticator())
                .setAuthorizer(new DropwizardBlogAuthorizer()).setPrefix(BEARER).buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);*/

        // Register resources
       
        environment.jersey().register(new PartsResource(dbi.onDemand(PartsService.class)));
      }

}
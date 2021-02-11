package com.example.restaurantapi;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
public class RestaurantApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RestaurantApiApplication.class);
	}

	@Bean
	public ServletContextInitializer servletInitializer() {
		return new ServletContextInitializer() {

			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				final ServletRegistration.Dynamic appServlet = servletContext.addServlet("jersey-servlet", new SpringServlet());
				Map<String, String> filterParameters = new HashMap<>();
				// Set filter parameters
				filterParameters.put("javax.ws.rs.RestaurantApiApplication", "com.example.restaurantapi.DemoConfig");
				appServlet.setInitParameters(filterParameters);
				appServlet.setLoadOnStartup(2);
				appServlet.addMapping("/*");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApiApplication.class, args);
	}

}

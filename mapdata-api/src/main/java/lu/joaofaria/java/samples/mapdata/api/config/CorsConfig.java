package lu.joaofaria.java.samples.mapdata.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Allow requests from your frontend application's domain(s)
				registry.addMapping("/api/v1/**")
						.allowedOrigins("*")
						.allowedHeaders("Content-Type", "Authorization")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
}
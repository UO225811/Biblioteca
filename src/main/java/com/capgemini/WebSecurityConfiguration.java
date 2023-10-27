package com.capgemini;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfiguration {

	/**
	 * Configura el encriptador de contraseñas a utilizar en la aplicación.
	 * 
	 * @return el encriptador de contraseñas BCryptPasswordEncoder.
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configura el AuthenticationManager a utilizar en la autenticación.
	 * 
	 * @param auth la configuración de autenticación.
	 * @return el AuthenticationManager configurado.
	 * @throws Exception si ocurre algún error al obtener el AuthenticationManager.
	 */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}

	/**
	 * Configura las reglas de autorización para las rutas de la aplicación.
	 * 
	 * @param http el objeto HttpSecurity a configurar.
	 * @return el SecurityFilterChain configurado.
	 * @throws Exception si ocurre algún error al configurar las reglas de
	 *                   autorización.
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/add").hasAuthority("ADMIN")
                .requestMatchers("admin/save").hasAuthority("ADMIN")
                .requestMatchers("admin/delete/*").hasAuthority("ADMIN")
                .requestMatchers("admin/update/*").hasAuthority("ADMIN")
                .requestMatchers("/libro/add/*").hasAuthority("ADMIN")
//                .requestMatchers("/home").hasAnyAuthority("ADMIN","LECTOR")
                .requestMatchers("/signup").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/home").permitAll()
                .anyRequest()
                .authenticated())
				.formLogin(login -> login.loginPage("/login").permitAll()
                .defaultSuccessUrl("/home").failureUrl("/error")).logout(logout -> logout.logoutSuccessUrl("/login")
                .permitAll());

		return http.build();
	}

	/**
	 * Configura el objeto WebMvcConfigurer para permitir todas las peticiones CORS.
	 * 
	 * @return el WebMvcConfigurer configurado.
	 */
	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}
}
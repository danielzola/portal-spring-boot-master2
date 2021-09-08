package id.go.dephub.hubla.sehati.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
   protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
       		   .antMatchers("/portal", "/assets/**", "/map", "/gis/**", "/files/upload/verifypdf",
       				   		"/files/preview/verifypdf/**", "/verifysign", "/register", "/activate/*",
       				   		"/activate", "/forgotpass", "/resetpass", "/resetpass/*","/token/*")
               .permitAll()
               .anyRequest()
               .authenticated()
               .and().csrf().disable()
               .oauth2Login().loginPage("/portal")
               .and()
               .logout().logoutUrl("/applogout")
               .logoutSuccessHandler(oidcLogoutSuccessHandler());
   }

   @Autowired
   private ClientRegistrationRepository clientRegistrationRepository;

   @SuppressWarnings("deprecation")
	private LogoutSuccessHandler oidcLogoutSuccessHandler() {
       OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
       new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
       oidcLogoutSuccessHandler.setPostLogoutRedirectUri(URI.create("https://sehati.hubla.dephub.go.id"));
       return oidcLogoutSuccessHandler;
   }
}

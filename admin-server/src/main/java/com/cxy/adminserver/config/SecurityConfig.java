package com.cxy.adminserver.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


/**
 * 添加Spring Security配置以保护Admin用户界面
 * 1.将管理UI限制为使用HTTP基本身份验证和通过表单登录的经过身份验证的用户。
 * 2.登录页面本身和静态UI资源（javascript，HTML，CSS）是公共的。否则，您无法登录。
 * 3.然后有一个基于cookie的跨站点请求伪造保护。
 * 4.CSRF保护中忽略了某些路径 - 这是因为管理服务器当前缺乏适当的支持(actuator)。
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");
        //2
        http.authorizeRequests()
                .antMatchers(
                          "/img/**",
                        adminContextPath + "/login"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                //3
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //4
                .ignoringAntMatchers(
                        adminContextPath+"/instances",
                        adminContextPath+"/actuator/**",
                        adminContextPath + "/logout"
                );
    }

}

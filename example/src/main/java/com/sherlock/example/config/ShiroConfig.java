package com.sherlock.example.config;

import com.sherlock.example.shiro.filter.CustomAuthenticationFilter;
import com.sherlock.example.shiro.realm.UserNameRealm;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 15:48
 */
@Configurable
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean restShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new CustomAuthenticationFilter());

        Map<String, String> definition = new HashMap<>(2);
        definition.put("/captcha", "anon");
        definition.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(definition);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(Collections.singletonList(userNameRealm()));
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    @Bean
    public UserNameRealm userNameRealm() {
        return new UserNameRealm();
    }

}

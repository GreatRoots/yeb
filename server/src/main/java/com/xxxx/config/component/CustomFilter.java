package com.xxxx.config.component;

import com.xxxx.pojo.Menu;
import com.xxxx.pojo.Role;
import com.xxxx.service.IMenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Resource
    private IMenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus=menuService.queryAllRoles();
        for(Menu menu:menus){
            if (antPathMatcher.match(menu.getUrl(),requestUrl)) {
                List<String> list = new ArrayList<>();
                for (Role role : menu.getRoles()) {
                    String name = role.getName();
                    list.add(name);
                }
                String[] str = list.toArray(new String[0]);
                return SecurityConfig.createList(str);
            }
        }
        return SecurityConfig.createList("Role_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

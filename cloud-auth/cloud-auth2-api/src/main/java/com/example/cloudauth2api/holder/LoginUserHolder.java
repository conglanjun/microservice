package com.example.cloudauth2api.holder;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.example.cloudauth2api.model.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJson = new JSONObject(userStr);
        UserDTO user = new UserDTO();
        user.setUsername(userJson.getStr("user_name"));
        user.setId(userJson.getLong("id"));
        user.setRoles(Convert.toList(String.class, userJson.get("authorities")));
        return user;
    }
}

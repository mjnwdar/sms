package com.sherlock.example.rpc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sherlock.api.ApiShipmentService;
import com.sherlock.model.vo.UserVo;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 17:18
 */
@Component
public class LoginService {
    @Reference(version = "1.0.0")
    private ApiShipmentService apiShipmentService;

    public UserVo getUserInfo(final String userName, final String password) {
        return apiShipmentService.login(userName, password);
    }

    public Set<String> selectRoleNameByUserName(String userName) {
        return null;
    }

    public Set<String> selectPermsByUserName(String userName) {
        return null;
    }
}

package com.sherlock.shipment.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sherlock.shipment.common.validate.groups.Create;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 15:03
 */
@Data
public class User implements java.io.Serializable {
    private static final long serialVersionUID = -3200103254689137288L;

    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @JsonIgnore
    @NotBlank(message = "密码不能为空", groups = Create.class)
    private String password;

    @JsonIgnore
    private String salt;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String status;

    private Date lastLoginTime;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date modifyTime;

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", username='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            ", email='" + email + '\'' +
            ", status='" + status + '\'' +
            ", lastLoginTime=" + lastLoginTime +
            ", createTime=" + createTime +
            ", modifyTime=" + modifyTime +
            '}';
    }
}

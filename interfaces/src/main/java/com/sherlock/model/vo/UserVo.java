package com.sherlock.model.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 16:36
 */
@Data
public class UserVo implements Serializable {
    private Integer userId;
    private String userName;
    private String password;
    private String salt;
}

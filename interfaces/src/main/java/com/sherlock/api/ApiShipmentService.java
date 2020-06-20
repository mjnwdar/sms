package com.sherlock.api;

import com.sherlock.model.Parcel;
import com.sherlock.model.Shipment;
import com.sherlock.model.vo.UserVo;
import java.util.List;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/5 13:12
 */
public interface ApiShipmentService {

    /**
     * 分拣合并
     * @param addrKey 货运地址
     * @return
     */
    List<Parcel> merge(String addrKey);

    /**
     * 货运拆分
     * @param shipment 货运资源
     * @return
     */
    boolean split(Shipment shipment);

    /**
     * 用户登陆
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    UserVo login(String userName, String password);
}

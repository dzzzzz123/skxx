package org.example.service;

import org.example.util.ResultModel;

/**
 * @author dz
 * @date 2022-09-15
 */
public interface AdminService {
    /**
     * 管理员登录
     * @param aAccount
     * @param aPwd
     * @return
     */
    ResultModel login(String aAccount, String aPwd);
}

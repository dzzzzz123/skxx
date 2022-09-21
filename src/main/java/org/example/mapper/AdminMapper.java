package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Admin;

/**
 * @author dz
 * @date 2022-09-15
 */
public interface AdminMapper {

    /**
     * 查询管理员
     * @param aAccount
     * @param aPwd
     * @return
     */
    Admin selectAdmin(@Param("aAccount") String aAccount,@Param("aPwd") String aPwd);
}

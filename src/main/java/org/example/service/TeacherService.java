package org.example.service;

import org.example.entity.Teacher;
import org.example.util.ResultModel;

/**
 * @author dz
 * @date 2022-09-15
 */
public interface TeacherService {
    /**
     * 老师登录
     * @param tAccount
     * @param tPwd
     * @return
     */
    ResultModel login(String tAccount, String tPwd);

    /**
     * 根据老师id查询老师信息
     * @param teacherId
     * @return
     */
    ResultModel selectOne(String teacherId);

    /**
     * 修改老师信息
     * @param teacher
     * @return
     */
    ResultModel updateByAccount(Teacher teacher);

    /**
     * 老师列表
     * @param page
     * @param limit
     * @return
     */
    ResultModel teacherList(String page, String limit);

    /**
     * 删除老师
     * @param id
     * @return
     */
    ResultModel deleteTeacher(String id);

    /**
     * 添加一名老师
     * @param teacher
     * @return
     */
    ResultModel add(Teacher teacher);

    /**
     * 重置老师id
     * @param id
     * @return
     */
    ResultModel resetTeacher(String id);
}
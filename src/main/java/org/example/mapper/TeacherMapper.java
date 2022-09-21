package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Teacher;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-15
 */
public interface TeacherMapper {
    /**
     * 查询老师
     * @param tAccount
     * @param tPwd
     * @return
     */
    Teacher selectTeacher(@Param("tAccount") String tAccount, @Param("tPwd") String tPwd);

    /**
     * 根据id查询老师信息
     * @param tId
     * @return
     */
    Teacher selectOne(@Param("tId") Integer tId);

    /**
     * 修改老师信息
     * @param teacher
     * @return
     */
    int update(Teacher teacher);

    /**
     * 查询所有老师信息
     * @return
     */
    List<Teacher> queryAll();

    /**
     * 根据id删除老师信息
     * @param tId
     * @return
     */
    int delete(@Param("tId") Integer tId);

    /**
     * 添加一名老师
     * @param teacher
     * @return
     */
    int add(Teacher teacher);

    /**
     * 重置老师信息
     * @param tId
     * @return
     */
    int reset(@Param("tId") Integer tId);
}

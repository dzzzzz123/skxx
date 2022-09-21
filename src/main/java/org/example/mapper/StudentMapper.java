package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Student;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-15
 */
public interface StudentMapper {
    /**
     * 根据学生id查询学生信息
     * @param sId
     * @return
     */
    Student selectOne(Integer sId);

    /**
     * 查询学生
     * @param sAccount
     * @param sPwd
     * @return
     */
    Student selectStudent(@Param("sAccount") String sAccount, @Param("sPwd") String sPwd);

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    int update(Student student);

    /**
     * 使用分页查询所有学生信息
     * @return
     */
    List<Student> queryAll();

    /**
     * 根据id删除学生
     * @param sId
     * @return
     */
    int delete(@Param("sId") Integer sId);

    /**
     * 添加学生
     * @param student
     * @return
     */
    int add(Student student);

    /**
     * 重置学生信息
     * @param sId
     * @return
     */
    int reset(@Param("sId") Integer sId);

    /**
     * 在学生表中查询出所有优秀学生
     * @return
     */
    List<Student> selectMarStudent();

    /**
     * 设置学生为优秀校友
     * @param sId 学生id
     * @return
     */
    int toMar(@Param("sId") Integer sId);
}

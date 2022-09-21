package org.example.service;

import org.example.entity.Student;
import org.example.util.ResultModel;

/**
 * @author dz
 * @date 2022-09-15
 */
public interface StudentService {
    /**
     * 学生登录
     * @param sAccount
     * @param sPwd
     * @return
     */
    ResultModel login(String sAccount, String sPwd);

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    ResultModel updateByAccount(Student student);


    /**
     * 根据学生id查询学生
     * @param studentId
     * @return
     */
    ResultModel selectOne(String studentId);

    /**
     * 查询学生信息
     * @param page
     * @param limit
     * @return
     */
    ResultModel studentList(String page, String limit);

    /**
     * 根据id删除学生
     * @param id
     * @return
     */
    ResultModel deleteStudent(String id);

    /**
     * 添加学生
     * @param student
     * @return
     */
    ResultModel addStudent(Student student);

    /**
     * 重置学生信息
     * @param id
     * @return
     */
    ResultModel resetStudent(String id);
}

package org.example.service;

import org.example.entity.Student;
import org.example.util.ResultModel;

import java.util.Date;

/**
 * @author dz
 * @date 2022-09-15
 */
public interface StudentService {
    /**
     * 学生登录
     * @param sAccount
     * @param sPwd
     * @return ResultModel
     */
    ResultModel login(String sAccount, String sPwd);

    /**
     * 修改学生信息
     * @param student
     * @return ResultModel
     */
    ResultModel updateByAccount(Student student);


    /**
     * 根据学生id查询学生
     * @param studentId
     * @return ResultModel
     */
    ResultModel selectOne(String studentId);

    /**
     * 查询学生信息
     * @param page
     * @param limit
     * @return ResultModel
     */
    ResultModel studentList(String page, String limit);

    /**
     * 根据id删除学生
     * @param id
     * @return ResultModel
     */
    ResultModel deleteStudent(String id);

    /**
     * 添加学生
     * @param student
     * @return ResultModel
     */
    ResultModel addStudent(Student student);

    /**
     * 重置学生信息
     * @param id
     * @return ResultModel
     */
    ResultModel resetStudent(String id);

    /**
     * 搜索出所有优秀学生
     * @return ResultModel
     */
    ResultModel selectMarStudent();

    /**
     * 将学生提升为优秀校友
     * @param id 学生id
     * @return ResultModel
     */
    ResultModel toMarStudent(String id);
}

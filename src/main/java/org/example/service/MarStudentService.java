package org.example.service;

import org.example.entity.MarStudent;
import org.example.util.ResultModel;

/**
 * @author dz
 * @date 2022-09-21
 */
public interface MarStudentService {
    /**
     * 添加优秀校友
     * @param marStudent 优秀学生
     * @return ResultModel
     */
    ResultModel addMarStudent(MarStudent marStudent);

    /**
     * 初始化新优秀校友
     * @param marStudent 优秀校友
     */
    void initMarStudent(MarStudent marStudent);

    /**
     * 查询所有优秀校友
     * @return ResultModel
     */
    ResultModel marStudentList();
}

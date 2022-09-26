package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.MarStudent;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-21
 */
public interface MarStudentMapper {
    /**
     * 添加优秀学生
     * @param marStudent 优秀学生信息
     * @return 影响的行数
     */
    int add(MarStudent marStudent);

    /**
     * 初始化优秀学生
     * @param marStudent 优秀学生
     * @return 影响的行数
     */
    int init(MarStudent marStudent);

    /**
     * 查询所有
     * @return  List<MarStudent>
     */
    List<MarStudent> queryAll();

    /**
     * 判断是否已存在
     * @param sId
     * @return
     */
    int getMarStudent(@Param("sId") Integer sId);
}

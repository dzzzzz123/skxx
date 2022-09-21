package org.example.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * 获取Mapper接口的工具类
 * @author qiufen
 * @date 2022-08-11
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 1.创建SqlSession工厂，因为是单例，只需要执行1次
            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(
                            Resources.getResourceAsReader("mybatis-config.xml")
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单元测试才使用的方法
     * @param clazz
     * @param autoCommit
     * @param obj
     * @param <T>
     */
    public static <T> void getMapper(Class<T> clazz,
                                     boolean autoCommit,
                                     MapperOperation<T> obj) {
        //2.获取SqlSession
        // true 自动提交  false 手动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(autoCommit);
        //3.获取Mapper接口
        T mapper = sqlSession.getMapper(clazz);
        //4.调用接口的中方法
        obj.operation(mapper);
        //5.关闭sqlSession
        sqlSession.close();
    }


    public static SqlSession getSqlSession(boolean autoCommit){
        SqlSession sqlSession = sqlSessionFactory.openSession(autoCommit);
        return sqlSession;
    }

    public static <T> T getMapper(Class<T> clazz,SqlSession sqlSession) {
        //3.获取Mapper接口
        T mapper = sqlSession.getMapper(clazz);
        return mapper;
    }
}

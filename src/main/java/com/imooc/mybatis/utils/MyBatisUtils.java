package com.imooc.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;

    // 初始化静态对象
    static {
        Reader reader = null;
        try {
            // 利用Reader加载classpath下的mybatis-config.xml核心配置文件
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            // 初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
            // 初始化错误
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public static void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}

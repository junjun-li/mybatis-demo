package com.imooc.mybatis;

import com.imooc.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
// 使用了mybatis，则尽量避免导入 java.sql 包
// import java.sql.Connection;

public class MyBatisTest {
    @Test
    public void SqlSessionFactoryTest() throws IOException {
        // 利用Reader加载classpath下的mybatis-config.xml核心配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

        // 初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = null;
        // 注意sql有开就要有关闭
        try {
            // 创建SqlSession对象，SqlSession是JDBC的扩展类，用于与数据库交互
            sqlSession = sqlSessionFactory.openSession();
            System.out.println("SqlSessionFactory加载成功");
            // 创建数据库连接(测试用)
            // Connection connection = sqlSession.getConnection();
            // System.out.println("connection");
            // System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                // 如果type="POOLED",代表使用连接池,close则是将连接回收到连接池中
                // 如果type="UNPOOLED",代表直连,close则会调用Connection.close()方法关闭连接
                sqlSession.close();
            }
        }

    }


    @Test // 用工具类创建和销毁的实例
    public void MyBatisUtilsTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            System.out.println(sqlSession);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }
}

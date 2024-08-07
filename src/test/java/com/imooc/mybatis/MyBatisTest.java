package com.imooc.mybatis;

import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import com.imooc.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Test
    public void selectAllTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Goods> goodsList = sqlSession.selectList("goods.selectAll");
            for (Goods goods : goodsList) {
                System.out.println(goods.getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void selectByIdTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 1240);
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void selectPriceRangeTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map params = new HashMap<>();
            params.put("min", 50);
            params.put("max", 100);
            params.put("pageSize", 10);
            List<Goods> goods = sqlSession.selectList("goods.selectPriceRange", params);
            for (Goods good : goods) {
                System.out.println(good.getGoodsId() + " " + good.getTitle() + ": " + good.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void selectGoodsLinkedHashMapTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Map> list = sqlSession.selectList("goods.selectGoodsLinkedHashMap");
            for (Map item : list) {
                System.out.println(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void selectGoodsDTOTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<GoodsDTO> list = sqlSession.selectList("goods.selectGoodsDTO");
            for (GoodsDTO item : list) {
                System.out.println(item);
                // System.out.println(item.getGoods());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void insertTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品0805");
            goods.setSubTitle("SubTitle");
            goods.setOriginalCost(200f);
            goods.setCurrentPrice(100f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);
            Integer num = sqlSession.insert("goods.insert", goods);
            System.out.println(num);
            sqlSession.commit();
            System.out.println(goods.getGoodsId());
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void updateTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 2684);
            goods.setTitle("测试商品-0806修改");
            int num = sqlSession.update("goods.update", goods);
            System.out.println("num: " + num);
            //
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void deleteTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            int delete = sqlSession.delete("goods.delete", 2684);
            System.out.println("delete: " + delete);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void dynamicTest() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map<String, Number> params = new HashMap<>();
            params.put("categoryId", 44);
            params.put("currentPrice", 100);
            List<Goods> goodsList = sqlSession.selectList("goods.dynamic", params);
            for (Goods item : goodsList) {
                System.out.println(item.getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }

    }
}

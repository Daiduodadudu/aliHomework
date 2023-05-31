package com.ebanma.cloud.usertestall.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * @author : 连峰
 * @version $ Id: DaoUtils, v 0.1 2023/03/30 10:07 banma- Exp $
 */
public class DaoUtils {
    private static SqlSessionFactory factory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.err.println("read mybatis-config.xml failed");
            e.printStackTrace();
            System.exit(1);
        }
        //加载mybatis-config.xml配置文件，并创建sqlSessionfactory对象
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static <R> R execute(Function<SqlSession, R> function) {
        //创建sqlsession
        SqlSession session = factory.openSession();
        try {
            R apply = function.apply(session);
            //提交事务
            session.commit();
            return apply;
        } catch (Throwable e) {
            session.rollback();
            System.out.println("execute error");
            throw e;
        } finally {
            session.close();
        }
    }
}
































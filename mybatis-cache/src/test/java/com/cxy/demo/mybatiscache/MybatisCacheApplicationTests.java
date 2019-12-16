package com.cxy.demo.mybatiscache;


import com.cxy.demo.mybatiscache.entity.StudentEntity;
import com.cxy.demo.mybatiscache.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisCacheApplicationTests {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void contextLoads() {
        System.out.println("本地缓存范围: " + sqlSessionFactory.getConfiguration().getLocalCacheScope());
    }

    /**
     *   一级缓存 ,sqlSession级别的
     *   <setting name="localCacheScope" value="SESSION"/>
     *   <setting name="cacheEnabled" value="true"/>
     * @throws Exception
     */
    @Test
    public void testLocalCache() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动提交事务
        StudentMapper innerstudentMapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(innerstudentMapper.getStudentById(1));
        System.out.println(innerstudentMapper.getStudentById(1));
        System.out.println(innerstudentMapper.getStudentById(1));
        sqlSession.close();
    }


    /**
     *   一级缓存 ,一个会话中，进行修改，将失效
     *   <setting name="localCacheScope" value="SESSION"/>
     *   <setting name="cacheEnabled" value="true"/>
     * @throws Exception
     */
    @Test
    public void testLocalCacheClear() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        StudentMapper innerstudentMapper = sqlSession.getMapper(StudentMapper.class);//自动提交事务
        System.out.println(innerstudentMapper.getStudentById(1));
        System.out.println("增加了" + innerstudentMapper.addStudent(buildStudent()) + "个学生");
        System.out.println(innerstudentMapper.getStudentById(1));
        sqlSession.close();
    }


    /**
     *   一级缓存只再sqlSession间共享
     *   <setting name="localCacheScope" value="SESSION"/>
     *   <setting name="cacheEnabled" value="true"/>
     * @throws Exception
     */
    @Test
    public void testLocalCacheScope() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

        StudentMapper studentMapper1 = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);

        System.out.println("studentMapper1读取数据: " + studentMapper1.getStudentById(1));
        System.out.println("studentMapper1读取数据: " + studentMapper1.getStudentById(1));
        System.out.println("studentMapper2更新" + studentMapper2.updateStudentName("小岑",1) + "个学生的数据");
        System.out.println("studentMapper1读取数据: " + studentMapper1.getStudentById(1));
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));

    }

    private StudentEntity buildStudent(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("明明");
        studentEntity.setAge(20);
        return studentEntity;
    }
}

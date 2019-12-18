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
     *
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
     *
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

    /**
     *  SqlSession不提交事务commit(),二级缓存不生效
     *  <setting name="localCacheScope" value="SESSION"/>
     *   <setting name="cacheEnabled" value="true"/>
     * @throws Exception
     */
    @Test
    public void testCacheWithoutCommitOrClose() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

        StudentMapper studentMapper1 = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);

        System.out.println("studentMapper1读取数据: " + studentMapper1.getStudentById(1));
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));
    }

    /**
     *  SqlSession提交事务commit(),二级缓存生效
     *  <setting name="localCacheScope" value="SESSION"/>
     *  <setting name="cacheEnabled" value="true"/>
     * @throws Exception
     */
    @Test
    public void testCacheWithCommitOrClose() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true); // 自动提交事务
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true); // 自动提交事务

        StudentMapper studentMapper1 = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);

        System.out.println("studentMapper1读取数据: " + studentMapper1.getStudentById(1));
        sqlSession1.commit();
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));

    }


    /**
     *  update()操作，并提交事务后，sqlsession2的StudentMapper 查询数据库，没有走Cache。
     *  <setting name="localCacheScope" value="SESSION"/>
     *   <setting name="cacheEnabled" value="true"/>
     * @throws Exception
     */
    @Test
    public void testCacheWithUpdate() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true); // 自动提交事务
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true); // 自动提交事务
        SqlSession sqlSession3 = sqlSessionFactory.openSession(true); // 自动提交事务


        StudentMapper studentMapper1 = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);
        StudentMapper studentMapper3 = sqlSession3.getMapper(StudentMapper.class);


        System.out.println("studentMapper1读取数据: " + studentMapper1.getStudentById(1));
        sqlSession1.close();
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));

        studentMapper3.updateStudentName("方方",1);
        sqlSession3.commit();
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));
    }


}

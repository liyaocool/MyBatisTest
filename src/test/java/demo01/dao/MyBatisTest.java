package demo01.dao;


import demo01.pojo.Dept;
import demo01.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;


public class MyBatisTest {
    private Reader reader = null;
    private SqlSession session = null;

    @Before
    public void open() {

        try {
            //1.读取配置文件
            reader = Resources.getResourceAsReader("MyBatisConfig.xml");
            //2.创建SqlSessionFactory工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //3.使用工厂生产SqlSession对象
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @After
    public void close() {
        //释放资源
        session.close();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertDept() {

        try {
            Dept dept = new Dept();
            dept.setDeptno(19);
            dept.setDname("软件部");
            dept.setLoc("江苏");
            session.insert("demo01.dao.DeptMapper.addDept", dept);
            session.commit();
            System.out.println("增加部门成功:" + dept.toString());

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    @Test
    public void updateDept() {

        try {
            Dept dept = new Dept();
            dept.setDeptno(19);
            dept.setDname("测试部");
            dept.setLoc("徐州");
            session.update("demo01.dao.DeptMapper.updateDept", dept);
            session.commit();
            System.out.println("修改部门成功:" + dept.toString());

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    @Test
    public void insertEmp() {

        try {
            Emp emp = new Emp();
            emp.setEmpno(66);
            emp.setEname("关羽");
            emp.setJob("将军");
            emp.setMgr(10086);
            emp.setHiredate(new Date());
            emp.setSal(2333.33);
            emp.setComm(6.66);
            emp.setDeptno(19);
            session.insert("demo01.dao.EmpMapper.addEmp", emp);
            session.commit();
            System.out.println("增加员工成功:" + emp.toString());

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    @Test
    public void updateEmp() {

        try {
            Emp emp = new Emp();
            emp.setEmpno(66);
            emp.setEname("张飞");
            emp.setJob("将军");
            emp.setMgr(10086);
            emp.setHiredate(new Date());
            emp.setSal(2333.33);
            emp.setComm(6.66);
            emp.setDeptno(19);
            session.update("demo01.dao.EmpMapper.updateEmp", emp);
            session.commit();
            System.out.println("修改员工成功:" + emp.toString());

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }
}

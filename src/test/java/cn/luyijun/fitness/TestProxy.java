package cn.luyijun.fitness;

import cn.luyijun.fitness.entity.UserInfo;
import cn.luyijun.fitness.dao.LuyUserLoginDao;
import cn.luyijun.fitness.interceptor.AddUserInfoProxy;
import cn.luyijun.fitness.service.LuyUserLoginService;
import cn.luyijun.fitness.service.LuyUserLoginServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@ComponentScan("cn.zhishu.finance.creaditcard.query")
//@WebAppConfiguration
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class TestProxy  {

    @Resource
    private LuyUserLoginService luyUserLoginService;
    @Resource
    private LuyUserLoginDao luyUserLoginDao;


    public int print(int a){
        System.out.println(a);
        return a;
    }

    @Test
    public  void main() {
//        int a = 0;
//        for (print(a++),print(++a); a < 7 && print(a++)<8 ; print(++a)) {
//            System.out.println("========");
//        }

        int i = 0;
        boolean matches = false;
        String str = "asfd";
        try {
            matches = str.matches("\\d+");
            i = Integer.parseInt(str);

        }catch (Exception e){

        }
        System.out.println(matches);
        System.out.println(i);
    }


    @Test
    public void test() throws Exception {
        String s1 = "a";
        String s2 = s1 + "b";
//        String s3 = "a" + "b";
        System.out.println(s2 == "ab");
//        System.out.println(s3 == "ab");

        LuyUserLoginServiceImpl target = new LuyUserLoginServiceImpl();
//        target.insert(userInfo);
        AddUserInfoProxy handler  = new AddUserInfoProxy(target);
//        luyUserLoginDao.insert(userInfo);
        Method insert = null;
//        Object[] objects = {userInfo};

        LuyUserLoginService luy = (LuyUserLoginService)Proxy.newProxyInstance(LuyUserLoginService.class.getClass().getClassLoader(),
                LuyUserLoginService.class.getClass().getInterfaces(), handler);

        try {
            insert = LuyUserLoginServiceImpl.class.getMethod("insert", UserInfo.class);
            Method declaredMethod = target.getClass().getDeclaredMethods()[0];
//            Object invoke = handler.invoke(LuyUserLoginServiceImpl.class, insert, objects);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        LuyUserLoginService proxyObject  =
//                (LuyUserLoginService) Proxy.newProxyInstance(AddUserInfoProxy.class.getClassLoader(), target.getClass().getInterfaces(), handler);
//        proxyObject.insert(userInfo);

//        LuyUserLoginService luyUserLoginService = (LuyUserLoginService) addUserInfoProxy.CreateProxyObj();

    }

}

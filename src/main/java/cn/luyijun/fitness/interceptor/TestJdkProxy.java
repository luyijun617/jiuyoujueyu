package cn.luyijun.fitness.interceptor;

import cn.luyijun.fitness.entity.UserInfo;
import cn.luyijun.fitness.service.Arithmetic;
import cn.luyijun.fitness.service.Impl.ArithmeticImpl;
import cn.luyijun.fitness.service.LuyUserLoginService;
import cn.luyijun.fitness.controller.ProxyObject;

import javax.annotation.Resource;

public class TestJdkProxy {

    @Resource
    LuyUserLoginService luyUserLoginService;

    public static void main(String[] args) {
        //目标对象
        Arithmetic target = new ArithmeticImpl();
        //获取代理对象
        Object proxy1 = new ProxyObject(target).getProxy();
        Arithmetic proxyObject = (Arithmetic)new ProxyObject(target).getProxy();
        proxyObject.add(1,2);
        proxyObject.mul(1,2);


        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("user");
        userInfo.setPassword("abcdefg");



//        LuyUserLoginService target = new LuyUserLoginServiceImpl();
//        ObjectProxy objectProxy = new ObjectProxy(target);
//        Object proxy = objectProxy.getProxy();
//        LuyUserLoginService proxy1 = (LuyUserLoginService) proxy;
//        proxy1.insert(userInfo);


//        AddUserInfoProxy handler  = new AddUserInfoProxy(target);
//        Object obj01 = handler.createProxyIntance(target);
//        Object obj03 = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
//        Object obj02 = Proxy.newProxyInstance(LuyUserLoginServiceImpl.class.getClass().getClassLoader(), LuyUserLoginServiceImpl.class.getClass().getInterfaces(), handler);
//        LuyUserLoginService luy1 = (LuyUserLoginService)obj01;
//        LuyUserLoginService luy2 = (LuyUserLoginService)obj02;
//System.out.println(obj01.equals(obj02));
//System.out.println(obj01.equals(obj03));
//System.out.println(obj02.equals(obj03));


    }

}

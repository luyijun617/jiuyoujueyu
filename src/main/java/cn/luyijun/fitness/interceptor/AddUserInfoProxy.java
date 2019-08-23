package cn.luyijun.fitness.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AddUserInfoProxy implements InvocationHandler {

    private Object target;

    public AddUserInfoProxy(Object target) {

        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始jdk 动态代理...");
        Object invoke = method.invoke(target, args);
//        System.out.println("结束jdk 动态代理...");
        return invoke;
    }

//    // 生成代理类
//    public Object createProxyInstance(Object target){
//        this.target=target;
//        //第一个参数是目标对象的类加载器
//        //第二个参数是目标对象实现的接口
//        //第三个参数传入一个InvocationHandler实例，该参数和回调有关系。
//        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
//    }





}

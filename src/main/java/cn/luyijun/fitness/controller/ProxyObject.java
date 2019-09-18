package cn.luyijun.fitness.controller;

import cn.luyijun.fitness.controller.MyInvocationHandler;
import cn.luyijun.fitness.dao.LuyUserLoginDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyObject {
    //目标对象
    private Object target;

    public ProxyObject() {
    }
    public ProxyObject(Object target) {
        this.target = target;
    }

    //获取代理对象
    public Object getProxy() {
        //代理对象
        Object proxy = null;
//        ClassLoader loader : 目标对象的类加载器
//        Class<?>[] interfaces : 接口们，目标对象提供的所有接口对象
//        InvocationHandler h :  代理类的调用处理对象需要实现的接口
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler h = new MyInvocationHandler(target);
        //这里创建代理对象，使得代理对象和目标对象拥有相同的方法行为，h是代理对象调用处理执行的方法。
        proxy = Proxy.newProxyInstance(loader, interfaces, h);
        return proxy;
    }


    // 生成代理类
    public Object createProxyInstance(){
        this.target=target;
        //第一个参数是目标对象的类加载器
        //第二个参数是目标对象实现的接口
        //第三个参数传入一个InvocationHandler实例，该参数和回调有关系。
        Object o = Proxy.newProxyInstance(LuyUserLoginDao.class.getClassLoader(), LuyUserLoginDao.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj = method.invoke(LuyUserLoginDao.class, args);
                return obj;
            }
        });
        return o;
    }
}

package cn.luyijun.fitness.interceptor;

import cn.luyijun.fitness.luyservice.service.Arithmetic;
import cn.luyijun.fitness.luyservice.service.Impl.ArithmeticImpl;

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

    // 生成代理类
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


    public Object createProxyInstance(){
        this.target=target;
        Object proxy = Proxy.newProxyInstance(ArithmeticImpl.class.getClassLoader(), ArithmeticImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj = method.invoke(new ArithmeticImpl(), args);
                return obj;
            }
        });
        return proxy;
    }

    /**
     * @description test JdkProxy
     * @author lyj
     * @date 2019/9/24 15:12
     */
    public static void main(String[] args) {
//        Arithmetic target = new ArithmeticImpl();
//        //被代理对象
//        ProxyObject proxyObject = new ProxyObject(target);
//        //被代理对象实例
//        Object proxy = proxyObject.getProxy();
//        Arithmetic proxy1 = (Arithmetic) proxy;
//        //调用方法
//        int result = proxy1.add(1, 2);
//        System.out.println(result);

        System.out.println("-------------------");
        ProxyObject proxyObject1 = new ProxyObject();
        Object proxyInstance = proxyObject1.createProxyInstance();
        Arithmetic proxyInstance1 = (Arithmetic) proxyInstance;
        System.out.println(proxyInstance1);
        int add = proxyInstance1.add(1, 1);
        System.out.println(add);





    }
}

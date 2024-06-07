package org.relaxcg.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyExample {
    public static void main(String[] args) {
        // 定义拦截器
        MethodInterceptor interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("Before method call");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("After method call");
                return result;
            }
        };

        // 配置增强器
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyTargetClass.class);  // 设置要代理的类
        enhancer.setCallback(interceptor);           // 设置拦截器

        // 创建代理对象
        MyTargetClass proxyInstance = (MyTargetClass) enhancer.create();

        // 调用代理对象的方法
        proxyInstance.someMethod();
    }
}

class MyTargetClass {
    public void someMethod() {
        System.out.println("Original method execution");
    }
}

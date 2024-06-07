package org.relaxcg.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author relaxcg
 * @date 2024/4/29 14:56
 */
public class ProxyDemo {

    public void doSomething() {
        System.out.println("doSomething");
    }

    public static void main(String[] args) {
        // Interface1 proxy1 = (Interface1) JdkProxy.getProxy(new Target1());
        // System.out.println(proxy1.getClass().getSimpleName());
        // proxy1.doSomething();


        ProxyDemo proxy = (ProxyDemo) new CGLIBDynamicProxy().getProxy(new ProxyDemo());
        proxy.doSomething();
    }
}

class JdkProxy {

    // private Object target;

    /**
     * 获取JDK代理
     *
     * @param target 代理目标
     * @return java.lang.Object 代理对象
     * @author relaxcg
     * @date 2024/4/29 15:04
     */
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(proxy.getClass().getSimpleName());
                        System.out.println("before");
                        Object result = method.invoke(target, args);
                        System.out.println("after");
                        return result;
                    }
                });
    }
}

interface Interface1 {
    void doSomething();
}

class Target1 implements Interface1 {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }
}

class Target2 {
    public void doSomething() {
        System.out.println("doSomething");
    }
}

class CGLIBDynamicProxy implements MethodInterceptor {
    public CGLIBDynamicProxy() {}
    Object target;

    public Object getProxy(Object target) {
        this.target = target;
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        // enhancer.setClassLoader(target.getClass().getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(target.getClass());
        // 设置方法拦截器
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // o 被代理的对象
        // method 被拦截的方法
        // args 方法参数
        // methodProxy 方法代理, 用于调用原始方法
        // before
        System.out.println("before");
        Object result = methodProxy.invokeSuper(target, args);
        // after
        System.out.println("after");
        return result;
    }
}

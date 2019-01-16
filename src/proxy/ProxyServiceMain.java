package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyServiceMain {

    public static void main(String args[]){
        ProxyService serviceTarget = new ProxyService(){};
        ProxyService proxyService = (ProxyService)Proxy.newProxyInstance(ProxyServiceMain.class.getClassLoader(), new Class[]{ProxyService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("begin proxy");
                try {
                    return method.invoke(serviceTarget, args);
                } finally {
                    System.out.println("end proxy");
                }
            }
        });
        proxyService.runBussiness();
        System.out.println(proxyService.work("Simba"));
    }
}

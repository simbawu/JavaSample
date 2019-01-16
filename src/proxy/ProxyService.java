package proxy;

public interface ProxyService {
    default void runBussiness(){
        System.out.println("Service work");
    }
    default String work(String name){
        return name + " is working.";
    }
}

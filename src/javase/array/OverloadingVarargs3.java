package javase.array;

public class OverloadingVarargs3 {
    static void f(float i, Character... args){
        System.out.println("first:" + i);
    }

//    static void f(Character... args){
//        System.out.println("second");
//    }
    static void f(char a, Character... args){
        System.out.println("second");
    }

    public static void main(String[] args){
        f(1, 'b');
        f('a', 'b');
    }

}

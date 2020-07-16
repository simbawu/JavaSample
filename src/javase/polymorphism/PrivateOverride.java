// polymorphism/PrivateOverride.java
// Trying to override a private method
// {java polymorphism.PrivateOverride}
package javase.polymorphism;
public class PrivateOverride {
    public int field = 0;
    private int field2 = 0;
    public int getField() {
        return field;
    }
    public int getField2() {
        return field2;
    }
    private void f() {
        System.out.println("private f()");
    }
    public static void main(String[] args) {
        PrivateOverride po = new Derived1();
        Derived1 derived1 = new Derived1();
        po.f();
        derived1.f();
        Derived1 p =(Derived1)po;
        p.f();

        System.out.println("po.field = " + po.field +
                ", po.getField() = " + po.getField());
        System.out.println("derived1.field = " + derived1.field +
                ", derived1.getField() = " + derived1.getField()
                + ", derived1.getSuperField() = " + derived1.getSuperField()
                + ", derived1.getField2() = " + derived1.getField2());
        Derived2 derived2 = new Derived2();
        System.out.println("derived2.field = " + derived2.field +
                ", derived2.getField() = " + derived2.getField()
                + ", derived2.getSuperField() = " + derived2.getSuperField()
                + ", derived2.getField2() = " + derived2.getField2());
        System.out.println("p.field = " + p.field +
                ", p.getField() = " + p.getField()
                + ", p.getSuperField() = " + p.getSuperField());
    }
}
class Derived1 extends PrivateOverride {
    public int field = 1;
    private int field2 = 2;

    public int getSuperField() {
        return super.field;
    }

    public void f() {
        System.out.println("public f()");
    }
}

class Derived2 extends PrivateOverride {
    public int field = 1;
    private int field2 = 2;

    @Override
    public int getField() {
        return field;
    }

    public int getSuperField() {
        return super.field;
    }
    @Override
    public int getField2() {
        return field2;
    }

    public void f() {
        System.out.println("public f()");
    }
}
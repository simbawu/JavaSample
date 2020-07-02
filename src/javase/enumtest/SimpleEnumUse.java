package javase.enumtest;

public class SimpleEnumUse{
    public static void main(String[] args){
        Spiciness spiciness = Spiciness.FLAMING;

        System.out.println(spiciness + " " + spiciness.ordinal());//order of enum
    }


}

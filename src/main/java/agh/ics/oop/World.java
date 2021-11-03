package agh.ics.oop;

public class World {



    public static void main(String[] args) {


        System.out.println("system wystartował");

        Animal zwierze = new Animal();
        System.out.println(zwierze.toString());
        zwierze.move(MoveDirection.RIGHT);
//        zwierze.move(MoveDirection.FORWARD);
//        zwierze.move(MoveDirection.FORWARD);
//        zwierze.move(MoveDirection.FORWARD);
        System.out.println(zwierze.toString());
        System.out.println("system zakończył działanie");
    }

}

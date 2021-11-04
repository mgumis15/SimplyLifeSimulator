package agh.ics.oop;

public class World {



    public static void main(String[] args) {


        System.out.println("system wystartował");
        OptionsParser parser=new OptionsParser();
        Animal zwierze = new Animal();
        System.out.println(zwierze.toString());
        MoveDirection[] directions=parser.parse(args);
        for (MoveDirection dir:directions) {
            zwierze.move(dir);
        }
        System.out.println(zwierze.toString());
        System.out.println("system zakończył działanie");
    }

}

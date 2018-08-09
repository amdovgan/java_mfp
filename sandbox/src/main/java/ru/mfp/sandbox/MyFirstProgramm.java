package ru.mfp.sandbox;

public class MyFirstProgramm {

  public static void main(String [] args) {	
    hello("Andrey");
    hello("user");
    hello("world");



    Square si = new Square(3);
    System.out.println("Площадь квадрата со стороной " + si.side + " = " + si.area());

    Rectangle re = new Rectangle(4,5);
    System.out.println("Площадь прямоугольника со сторонами " + re.a + " $ " + re.b + " = " + re.area());

  }

  public static void hello(String nobody) {
    System.out.println("Hello, " + nobody + "!");
  }

}



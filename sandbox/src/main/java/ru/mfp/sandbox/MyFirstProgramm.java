package ru.mfp.sandbox;

public class MyFirstProgramm {

  public static void main(String [] args) {	
    hello("Andrey");
    hello("user");
    hello("world");

    double len = 3;
    double a = 4;
    double b = 5;

    System.out.println("Площадь квадрата со стороной " + len + " = " + area(len));

    System.out.println("Площадь прямоугольника со сторонами " + a + " $ " + b + " = " + area(a,b));

  }

  public static void hello(String nobody) {
    System.out.println("Hello, " + nobody + "!");
  }


  public static double area(double side) {
    return side * side;
  }

  public static double area(double a, double b) {
    return a * b;

  }
} 


package ru.mfp.sandbox;

public class MyFirstProgramm {

  public static void main(String [] args) {	
    hello("Andrey");
    hello("user");
    hello("world");

    double len = 3;
    System.out.println("Площадь квадрата со стороной " + len + " = " + area(len));

  }

  public static void hello(String nobody) {
    System.out.println("Hello, " + nobody + "!");
  }

  public static double area(double side) {
    return side*side;
  }
} 


package ru.mfp.sandbox;

public class MyFirstProgramm {

  public static void main(String [] args) {	
/*
    hello("Andrey");
    hello("user");
    hello("world");



    Square si = new Square(3);
    System.out.println("Площадь квадрата со стороной " + si.side + " = " + si.area());

    Rectangle re = new Rectangle(4,5);
    System.out.println("Площадь прямоугольника со сторонами " + re.a + " $ " + re.b + " = " + re.area());
*/

    Point p1 = new Point(3,4);
    Point p2 = new Point(8,7);

    //выводит результат из метода distance(double х, double у) который на вход принимает атрибуты класса
    System.out.println("Расстояние между точкой " + p1.x + " " + p1.y +" и " + " точкой " + p2.x + " " + p2.y + " равно " + p1.distance(p2.x,p2.y));

    //выводит результат из метода distance(Point p) который на вход принимает объект
    System.out.println("Расстояние между точками равно " + p1.distance(p2));

  }
/*
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(((p2.x-p1.x)*(p2.x-p1.x))+((p2.y-p1.y)*(p2.y-p1.y)));
  }
*/
/*
  public static void hello(String nobody) {
    System.out.println("Hello, " + nobody + "!");
  }
*/

}



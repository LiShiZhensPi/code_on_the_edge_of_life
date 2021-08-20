package Test2;

import com.sun.prism.shader.DrawCircle_LinearGradient_PAD_AlphaTest_Loader;

public class Coordinates {
    private long x;
    private long y;
    Coordinates(long x,long y){
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Circle circle = new Circle(2,4,16);
        Rectangle rectangle = new Rectangle(5,7,12,14);
        System.out.println("The circle Area is "+circle.getArea());
        System.out.println("The circle Perimeter is "+circle.getPerimeter());
        System.out.println("The rectangle Area is "+rectangle.getArea());
        System.out.println("The rectangle Perimeter is "+circle.getPerimeter());

    }

}

interface Shape{
    double getArea();
    double getPerimeter();
}

class Rectangle extends Coordinates implements Shape{
    private long a;
    private long b;
    Rectangle(long x,long y,long a,long b){
        super(x,y);
        this.a = a;
        this.b = b;
    }

    @Override
    public double getArea() {
        return a*b;
    }

    @Override
    public double getPerimeter() {
        return 2*(a+b);
    }
}
class Circle extends Coordinates implements Shape{
    private long r;
    Circle(long x,long y,long r){
        super(x,y);
        this.r = r;
    }

    @Override
    public double getArea() {
        return Math.PI*r*r;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*r;
    }
}


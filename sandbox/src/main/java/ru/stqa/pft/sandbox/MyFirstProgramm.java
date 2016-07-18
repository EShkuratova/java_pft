package ru.stqa.pft.sandbox;

class MyFirstProgramm {

    public static void main(String[] args){

        System.out.println("Hello,world!");

        double offsetX=4;
        double offsetY=3;

      Point point1=new Point(offsetX,offsetY);


        point1.setX(1);
        point1.setY(9);

        if(point1.getDistance()==5)
        {
            System.out.println("Метод работает верно: расстояние между точками ("+point1.getX()+","+point1.getY()+")  и "+ "(" +(point1.getX()+offsetX)+ "," +(point1.getY()+offsetY)+") равно "+point1.getDistance());

        }
        else
        {
            System.out.println("Растояние между точками вычислено неверно. Ожидалось 5.0, а получилось  "+ point1.getDistance());
        }


}

    public static double distance(Point p1, Point p2){

        p1.getX();
        p2.getX();
        p1.getY();
        p2.getY();

        double distance=Math.sqrt(Math.pow((p1.getX()-p2.getX()),2)+Math.pow((p1.getY()-p2.getY()),2));
        return distance;
    }


}
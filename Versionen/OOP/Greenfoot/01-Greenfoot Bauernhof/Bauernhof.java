import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Bauernhof extends World {


    public Bauernhof() {
        super(12, 12, 50, true);
        setBackground("images/grass.png");
        setPaintOrder(Bauer.class, Kuh.class);

        /*addObject(new Bauer("Bauer Fritz"), 1,2);
        addObject(new Bauer("Bauer Bernd"), 1,4);

        addObject(new Kuh("Rosa"), 5,1);
        addObject(new Kuh("Lena"), 6,3);
        addObject(new Kuh("Karl"), 4,6);*/
    }

}

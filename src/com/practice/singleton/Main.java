package com.practice.singleton;

// Driver Code
class Main
{
    public static void main(String[] args)
    {
        // instantiating Singleton class with variable x
        Singleton x = Singleton.create();

        // instantiating Singleton class with variable y
        Singleton y = Singleton.create();

        // instantiating Singleton class with variable z
        Singleton z = Singleton.create();

        // changing variable of instance x
        x.str = x.str.toUpperCase();

        System.out.println("String from x is " + x.str);
        System.out.println("String from y is " + y.str);
        System.out.println("String from z is " + z.str);
        System.out.println("\n");

        // changing variable of instance x
        z.str = z.str.toLowerCase();

        System.out.println("String from x is " + x.str);
        System.out.println("String from y is " + y.str);
        System.out.println("String from z is " + z.str);
    }
}


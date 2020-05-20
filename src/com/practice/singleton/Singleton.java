package com.practice.singleton;

// Java program implementing Singleton class
// with method name as that of class
class Singleton
{
    // static variable single_instance of type Singleton
    private static Singleton singleInstance = null;

    // variable of type String
    public String str;

    // private constructor restricted to this class itself
    private Singleton() {
        str = "Hello I am a string part of Singleton class";
        System.out.println("Original string " + str);
    }

    // static method to create instance of Singleton class
    public static Singleton create() {
        // To ensure only one instance is created
        if (singleInstance == null) {
            singleInstance = new Singleton();
        }
        return singleInstance;
    }
}

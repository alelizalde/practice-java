package com.practice;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



public class InstallPackages {

    static class Package {

        boolean installed;
        List<Package> dependencies;
        int value;

        Package(int value) {
            this.value = value;
            this.installed = false;
            this.dependencies = new ArrayList<>();
        }
    }

    static List<Package> installed = new ArrayList<>();

    static public boolean install(Package pack) {
        Stack<Package> stack = new Stack<>();
        stack.push(pack);
        while (!stack.isEmpty()) {
            if (stack.peek().dependencies.size() > 0 && !isDependenciesInstalled(stack.peek())) {
                for (Package dependency : stack.peek().dependencies) {
                    stack.push(dependency);
                }
            } else {
                if (!isInstalled(stack.peek())) {
                    stack.peek().installed = true;
                    installed.add(stack.pop());
                } else {
                    stack.pop();
                }
            }
        }

        return true;
    }

    static public boolean isInstalled(Package pack) {
        for (Package current: installed) {
            if (current == pack) return true;
        }
        return false;
    }

    static public boolean isDependenciesInstalled(Package pack) {
        boolean result = true;
        for (Package dependency : pack.dependencies) {
           if(!isInstalled(dependency)) return false;
        }
        return result;
    }

    public static void main(String[] args) {
        Package p1 = new Package(1);
        Package p2 = new Package(2);
        Package p3 = new Package(3);
        Package p4 = new Package(4);
        Package p5 = new Package(5);
        Package p6 = new Package(6);
        /*
        p1 -> p2, p3
        p2 -> p5
        p3 -> p4, p6, p5
        */
        p1.dependencies.add(p2);
        p1.dependencies.add(p3);
        p2.dependencies.add(p5);
        p3.dependencies.add(p4);
        p4.dependencies.add(p6);
        p4.dependencies.add(p5);
        install(p1);
    }
}

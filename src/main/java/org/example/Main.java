package org.example;

public class Main {
    public static void main(String[] args) {
        Model model = new Model("src/main/java/org/example/log2023-01-14 18=13.txt");
        View view = new View();
        new Controller(model, view);

    }
}
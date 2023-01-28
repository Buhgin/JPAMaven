package org.example;



import org.example.username.com.da.jpa.entite.console.CategoryConsole;

public class Main {



    public static void main(String[] args) {
        CategoryConsole console = new CategoryConsole();
         console.create();
         console.selectAll();
         console.selectId();
         console.updateId();
         console.deleteId();
    }


}
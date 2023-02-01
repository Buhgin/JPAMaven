package org.example;


import org.example.username.com.da.jpa.entite.Category;
import org.example.username.com.da.jpa.entite.console.CategoryConsole;
import org.example.username.com.da.jpa.entite.console.OptionConsole;
import org.example.username.com.da.jpa.entite.console.ProductConsole;
import org.example.username.com.da.jpa.entite.console.ValueConsole;
import org.example.username.com.da.jpa.entite.manager.ThisEntity;

public class Main {

   private static final CategoryConsole categoryConsolesole = new CategoryConsole();
    private static final   ProductConsole productConsole = new ProductConsole();
    private static final   OptionConsole optionConsole = new OptionConsole();
    private static final   ValueConsole valueConsole = new ValueConsole();
    public static void main(String[] args) {


        try {
            ThisEntity.getEntityManagerFactory();
            ThisEntity.getEntityManager();
           // valueConsole.updateId();
            //valueConsole.deleteId();
            // optionConsole.selectAll();

            //  valueConsole.create();
            //create();
            //optionConsole.creater();
          //optionConsole.selectAll();
            //optionConsole.updateId();
        //    optionConsole.selectId();
           //optionConsole.deleteId();

            //productConsole.create();
            //  productConsole.selectAll();

           // productConsole.selectId();
            // productConsole.updateId();
            //  productConsole.deleteId();
            //categoryConsolesole.create();
          //categoryConsolesole.selectAll();
            //categoryConsolesole.selectId();
          //categoryConsolesole.updateId();


           categoryConsolesole.deleteId();
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            ThisEntity.shutdownEntityManagerFactory();
            ThisEntity.shutdownEntityManager();
        }


    }
    public static void create(){
        categoryConsolesole.create();
        optionConsole.creater();
        productConsole.create();
        valueConsole.create();

    }


}
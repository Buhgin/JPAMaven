package org.example.username.com.da.jpa.entite.console;


import org.example.username.com.da.jpa.entite.CRUD.CRUDManger;
import org.example.username.com.da.jpa.entite.CRUD.ProductCrudImpl;
import org.example.username.com.da.jpa.entite.Category;
import org.example.username.com.da.jpa.entite.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ProductConsole {
    private final CRUDManger<Product> crudManger = new ProductCrudImpl();
    private final CategoryConsole categoryConsole = new CategoryConsole();


    public void create() {
        List<Product> products = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name;
        Integer price;
        Category category;

        try {
            do {System.out.println("Введите имя продукта");
                name = br.readLine();
                System.out.println("Введите цену продукта");
                price = Integer.valueOf(br.readLine());
                System.out.println("Введите категорию продукта");
                category = categoryConsole.selectId();
                products.add(new Product(name, price, category));
                System.out.println("для завершения нажмите 1 для продолжения любую кнопку ");
            } while (!br.readLine().equals("1"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Product product : products) {
            System.out.printf("%s, %d CATEGORY ID: [%d]\n", product.getName()
                    , product.getPrice()
                    , product.getCategory().getId());
        }
        crudManger.create(products);

    }

    public Product selectId() {
        long id;
        selectAll();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id продукта для получения из списка");
            id = Long.parseLong(br.readLine());



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    return  crudManger.slectID(id);
    }


    public void selectAll() {
        crudManger.select();
    }

    public void updateId() {
        String name;
        Integer price;
        Category category;
        Long id;
        selectAll();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Укажите id продукта");
            id = Long.parseLong(br.readLine());
            System.out.println("Укажите новое имя продукта");
            name = br.readLine();
            System.out.println("Укажите новую цену продукта");
            price = Integer.valueOf(br.readLine());

            System.out.println("Укажите новую категорию продукта");
            category = categoryConsole.selectId();
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            crudManger.update(product);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteId() {
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id продукта для удаления");
            id = Long.parseLong(br.readLine());
            crudManger.delete(id);

        }catch (NumberFormatException e){
            System.err.println("Неверный формат введите число");
        } catch (IllegalArgumentException e) {
            System.err.println("Неверный id");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

package org.example.username.com.da.jpa.entite.console;

import org.example.username.com.da.jpa.entite.CRUD.CRUDManger;
import org.example.username.com.da.jpa.entite.CRUD.CategoryCrudIml;
import org.example.username.com.da.jpa.entite.Category;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;


public class CategoryConsole {
    private static final CRUDManger<Category> crudManger = new CategoryCrudIml();

    public void updateId() {
        String name;
        long id;
        selectAll();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Укажите id");
            id = Long.parseLong(br.readLine());
            System.out.println("Укажите новое имя");
            name = br.readLine();
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            crudManger.update(category);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public void create() {
        List<Category> categories = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя категории для окончания списка напишите exit");
        String text;

        try {
            while (!(text = br.readLine()).equals("exit")) {
                categories.add(new Category(text));

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Category c : categories)
            System.out.printf("CATEGORY: %s\n",c.getName());

        crudManger.create(categories);

    }

    public Category selectId() {
        selectAll();
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id категории для получения из списка");
            id = Long.parseLong(br.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return crudManger.slectID(id);
    }


    public void selectAll() {
        crudManger.select();
    }

    public void deleteId() {
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("\nВведите id категории для удаления");
            id = Long.parseLong(br.readLine());
            crudManger.delete(id);


        } catch (NumberFormatException e) {
            System.err.println("Неверный формат введите число");
        } catch (IllegalArgumentException e) {
            System.err.println("Неверный id");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

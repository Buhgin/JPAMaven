package org.example.username.com.da.jpa.entite.console;

import org.example.username.com.da.jpa.entite.CRUD.CRUDManger;
import org.example.username.com.da.jpa.entite.CRUD.CategoryCrudIml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CategoryConsole {
    private static final CRUDManger crudManger = new CategoryCrudIml();

    public void updateId() {
        String name;
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Укажите id");
            id = Long.parseLong(br.readLine());
            System.out.println("Укажите новое имя");
            name = br.readLine();
            crudManger.update(id, name);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public void create() {
        List<String> name = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя категории для окончания списка напишите exit");
        String text;

        try {
            while (!(text = br.readLine()).equals("exit")) {

                name.add(text);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (String s : name)
            System.out.println(s);

        crudManger.create(name);

    }

    public void selectId() {
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id категории");
            id = Long.parseLong(br.readLine());
            crudManger.slectID(id);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void selectAll() {
        crudManger.select();
    }

    public void deleteId() {
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id категории");
            id = Long.parseLong(br.readLine());
            crudManger.delete(id);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

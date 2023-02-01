package org.example.username.com.da.jpa.entite.console;

import org.example.username.com.da.jpa.entite.CRUD.CRUDManger;
import org.example.username.com.da.jpa.entite.CRUD.OptionsCrudImpl;
import org.example.username.com.da.jpa.entite.Category;
import org.example.username.com.da.jpa.entite.Option;
import org.example.username.com.da.jpa.entite.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OptionConsole {
    private final CRUDManger<Option> crudManger = new OptionsCrudImpl();

    private final CategoryConsole categoryConsole = new CategoryConsole();

    public void creater() {
        List<Option> optionsList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name;

        Category category;
        try {
            do {
                System.out.println("Введите имя характеристики");
                name = br.readLine();
                System.out.println("Введите категорию характеристики");
                category = categoryConsole.selectId();
                optionsList.add(new Option(name, category));
                System.out.println("для завершения нажмите 1 для продолжения любую кнопку ");
            } while (!br.readLine().equals("1"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Option option : optionsList)
            System.out.printf("%s, CATEGORY: (%d),\n ", option.getName(), option.getCategory().getId());
        crudManger.create(optionsList);

    }

    public void selectAll() {
        crudManger.select();
    }

    public Option selectId() {
        long id;
        selectAll();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id характеристики для получения из списка");
            id = Long.parseLong(br.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return crudManger.slectID(id);
    }

    public void updateId() {
        String name;
        Category category;
        Long id;
        selectAll();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Укажите id характеристики для изменения");
            id = Long.parseLong(br.readLine());
            System.out.println("Укажите новое имя характеристики");
            name = br.readLine();

            System.out.println("Укажите новую категорию продукта");
            category = categoryConsole.selectId();
            Option option = new Option();
            option.setId(id);
            option.setName(name);
            option.setCategory(category);
            crudManger.update(option);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteId() {
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id характеристики для удаления");
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


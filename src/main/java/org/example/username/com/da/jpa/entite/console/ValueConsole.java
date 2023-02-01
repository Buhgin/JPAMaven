package org.example.username.com.da.jpa.entite.console;

import org.example.username.com.da.jpa.entite.CRUD.CRUDManger;
import org.example.username.com.da.jpa.entite.CRUD.ValueCrudImpl;
import org.example.username.com.da.jpa.entite.Option;
import org.example.username.com.da.jpa.entite.Product;
import org.example.username.com.da.jpa.entite.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ValueConsole {
    private final CRUDManger<Value> crudManger = new ValueCrudImpl();
    private final OptionConsole optionConsole = new OptionConsole();
    private final ProductConsole productConsole = new ProductConsole();

    public void create() {
        List<Value> valueList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name;
        Product product;
        Option option;

        try {
            do {
                System.out.println("Введите значение характеристики");
                name = br.readLine();
                System.out.println("Выберете продукт");
                productConsole.selectAll();
                product = productConsole.selectId();
                System.out.println("выберете опцию продукта");
                optionConsole.selectAll();
                option = optionConsole.selectId();
                valueList.add(new Value(product, option, name));
                System.out.println("для завершения нажмите 1 для продолжения любую кнопку ");
            } while (!br.readLine().equals("1"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Value value : valueList)
            System.out.printf("VALUE: %s, %s, %s\n", value.getValue(),

                    value.getProduct().getName(),
                    value.getOption().getName());

        crudManger.create(valueList);

    }

    public Value selectId() {
        long id;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id значения для получения из списка");
            id = Long.parseLong(br.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return crudManger.slectID(id);
    }

    public void selectAll() {
        crudManger.select();
    }

    public void updateId() {
        String name;
        Product product;
        Option option;
        Option optionBefor;
        Product productBefor;
        Long id;
        selectAll();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Укажите id значения для изменения");
            id = Long.parseLong(br.readLine());
            System.out.println("Укажите новое значение");
            name = br.readLine();
            System.out.println("Укажите новую характеристику");
            optionBefor = crudManger.slectID(id).getOption();
            System.out.printf("Текущая характеристика: %s, [%d], CATEGORY iD: [%d]\n"
                    ,optionBefor.getName(), optionBefor.getId(), optionBefor.getCategory().getId());
            option = optionConsole.selectId();
            System.out.println("Укажите новый продукт");
            productBefor = crudManger.slectID(id).getProduct();
            System.out.printf("Текущий продукт: %s, [%d], CATEGORY iD: [%d]\n"
                    ,productBefor.getName(), productBefor.getId(), productBefor.getCategory().getId());
            product = productConsole.selectId();

            Value value = new Value();
            value.setId(id);
            value.setValue(name);
            value.setOption(option);
            value.setProduct(product);

            crudManger.update(value);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void deleteId() {
        long id;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите id значения для удаления");
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

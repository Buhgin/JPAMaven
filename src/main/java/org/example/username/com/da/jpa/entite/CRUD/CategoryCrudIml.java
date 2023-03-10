package org.example.username.com.da.jpa.entite.CRUD;

import jakarta.persistence.*;
import org.example.username.com.da.jpa.entite.Category;
import org.example.username.com.da.jpa.entite.manager.ThisEntity;


import java.util.List;


public class CategoryCrudIml implements CRUDManger<Category> {


    @Override
    public void create(List<Category> category) {

        try {
            category.forEach((k) -> {
                ThisEntity.getEntityManager().getTransaction().begin();
                ThisEntity.getEntityManager().persist(k);
                //System.out.println(k.toString());
                ThisEntity.getEntityManager().getTransaction().commit();

            });


        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }


    }

    @Override
    public void select() {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Category> categoryTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT c FROM Category c ", Category.class);

            List<Category> categoryList = categoryTypedQuery.getResultList();
            categoryList.forEach(p1 -> System.out.printf("%s [%d]\n", p1.getName(), p1.getId()));
            ThisEntity.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Category slectID(Long id) {


        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Category> categoryTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT c FROM Category c where c.id =" + id + "", Category.class);
            Category p1 = categoryTypedQuery.getSingleResult();
            System.out.printf("%s [%d]\n", p1.getName(), p1.getId());
            ThisEntity.getEntityManager().getTransaction().commit();
            return p1;
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void update(Category category) {

        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            ThisEntity.getEntityManager().merge(category);
            ThisEntity.getEntityManager().getTransaction().commit();
            System.out.printf("Updated:[%d], %s ", category.getId(), category.getName());

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            Category category = ThisEntity.getEntityManager().getReference(Category.class, id);
            ThisEntity.getEntityManager().remove(category);
            ThisEntity.getEntityManager().getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.err.println("Id ???? ????????????????????");
        }catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }
}


package org.example.username.com.da.jpa.entite.CRUD;

import jakarta.persistence.*;
import org.example.username.com.da.jpa.entite.Category;
import org.example.username.com.da.jpa.entite.manger.ThisEntity;


import java.util.List;


public class CategoryCrudIml implements CRUDManger {

    @Override
    public void create(List<String> name) {
        ThisEntity.getEntityManagerFactory();
        try {


            name.forEach((k) -> {
                ThisEntity.getEntityManager().getTransaction().begin();
                System.out.println(k);
                ThisEntity.getEntityManager().persist(new Category(k));
                ThisEntity.getEntityManager().getTransaction().commit();

            });


        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            ThisEntity.shutdownEntityManagerFactory();
            ThisEntity.shutdownEntityManager();
        }

    }

    @Override
    public void select() {
        ThisEntity.getEntityManagerFactory();
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Category> categoryTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT c FROM Category c ", Category.class);

            List<Category> categoryList = categoryTypedQuery.getResultList();
            categoryList.forEach(p1 -> System.out.printf("%s %d\n", p1.getName(), p1.getId()));
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            ThisEntity.shutdownEntityManagerFactory();
            ThisEntity.shutdownEntityManager();
        }
    }

    @Override
    public void slectID(Long id) {
        ThisEntity.getEntityManagerFactory();
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Category> categoryTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT c FROM Category c where c.id =" + id + "", Category.class);
            Category p1 = categoryTypedQuery.getSingleResult();
            System.out.printf("%s %d\n", p1.getName(), p1.getId());

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            ThisEntity.shutdownEntityManagerFactory();
            ThisEntity.shutdownEntityManager();
        }

    }


    @Override
    public void update(Long id, String name) {

        try {
            ThisEntity.getEntityManagerFactory();
            ThisEntity.getEntityManager().getTransaction().begin();
            Category c = new Category();
            c.setId(id);
            c.setName(name);
            ThisEntity.getEntityManager().merge(c);
            ThisEntity.getEntityManager().getTransaction().commit();


            System.out.println("Updated: " + c.getName());

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            ThisEntity.shutdownEntityManagerFactory();
            ThisEntity.shutdownEntityManager();
        }
    }

    @Override
    public void delete(Long id) {
        ThisEntity.getEntityManagerFactory();
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            Category category = ThisEntity.getEntityManager().getReference(Category.class, id);
            ThisEntity.getEntityManager().remove(category);
            ThisEntity.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            ThisEntity.shutdownEntityManagerFactory();
            ThisEntity.shutdownEntityManager();
        }
    }
}


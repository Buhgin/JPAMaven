package org.example.username.com.da.jpa.entite.CRUD;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

import org.example.username.com.da.jpa.entite.Value;
import org.example.username.com.da.jpa.entite.manager.ThisEntity;

import java.util.List;

public class ValueCrudImpl implements CRUDManger<Value> {
    @Override
    public void create(List<Value> values) {
        try {
            values.forEach((k)->{
                ThisEntity.getEntityManager().getTransaction().begin();
                ThisEntity.getEntityManager().persist(k);
                //System.out.println(k.toString());
                ThisEntity.getEntityManager().getTransaction().commit();

            });
        }catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void select() {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Value> categoryTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT v FROM Value v ", Value.class);

            List<Value> valuesList = categoryTypedQuery.getResultList();
            valuesList.forEach(p1 -> System.out.printf("VALUES : %s, [%d]\n", p1.getValue()
                    ,p1.getId()));
            ThisEntity.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Value slectID(Long id) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Value> valueTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT v FROM Value v where v.id =" + id + "", Value.class);
            Value p1 = valueTypedQuery.getSingleResult();
            System.out.printf("%s, %s, %s, %d $, [%d]\n", p1.getValue(),p1.getProduct().getName()
                    , p1.getOption().getName(),p1.getProduct().getPrice(), p1.getId());
            ThisEntity.getEntityManager().getTransaction().commit();
            return p1;
        }catch (EntityNotFoundException e) {
            System.err.println("Id не существует");
        }
        catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void update(Value value) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            ThisEntity.getEntityManager().merge(value);
            ThisEntity.getEntityManager().getTransaction().commit();
            System.out.printf("Updated:[%d], %s,%s %s\n", value.getId(),
                    value.getValue(),value.getOption().getName(),value.getProduct().getName());

        }
        catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            Value p = ThisEntity.getEntityManager().getReference(Value.class, id);
            ThisEntity.getEntityManager().remove(p);
            ThisEntity.getEntityManager().getTransaction().commit();
        }catch (EntityNotFoundException e) {
            System.err.println("Id не существует");
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }
    }


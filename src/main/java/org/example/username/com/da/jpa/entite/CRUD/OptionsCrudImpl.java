package org.example.username.com.da.jpa.entite.CRUD;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.example.username.com.da.jpa.entite.Option;
import org.example.username.com.da.jpa.entite.manager.ThisEntity;

import java.util.List;

public class OptionsCrudImpl implements CRUDManger<Option> {

    @Override
    public void create(List<Option> optionsList) {
        try {
            optionsList.forEach((k) -> {
                ThisEntity.getEntityManager().getTransaction().begin();
                ThisEntity.getEntityManager().persist(k);
                //  System.out.println(k.toString());
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
            TypedQuery<Option> categoryTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT o FROM Option o ", Option.class);

            List<Option> optionList = categoryTypedQuery.getResultList();
            optionList.forEach(p1 -> System.out.printf("OPTIONS: %s, [%d], CATEGORY: %s,[%d]\n", p1.getName()
                    , p1.getId(),p1.getCategory().getName(),p1.getCategory().getId()));
            ThisEntity.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Option slectID(Long id) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Option> optionTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT o FROM Option o where o.id =" + id + "", Option.class);
            Option p1 = optionTypedQuery.getSingleResult();
            System.out.printf("%s, %s,  [%d]\n", p1.getName()
                    , p1.getCategory().getName()
                    , p1.getId());
            ThisEntity.getEntityManager().getTransaction().commit();
            return p1;
        } catch (EntityNotFoundException e) {
            System.err.println("Id не существует");
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Option option) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            ThisEntity.getEntityManager().merge(option);
            ThisEntity.getEntityManager().getTransaction().commit();
            System.out.printf("Updated:[%d], %s, %s \n", option.getId(), option.getName()
                    , option.getCategory().getName());

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            Option o = ThisEntity.getEntityManager().getReference(Option.class, id);
            ThisEntity.getEntityManager().remove(o);
            ThisEntity.getEntityManager().getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.err.println("Id не существует");
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }

}


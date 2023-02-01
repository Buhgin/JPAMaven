package org.example.username.com.da.jpa.entite.CRUD;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.username.com.da.jpa.entite.Product;
import org.example.username.com.da.jpa.entite.manager.ThisEntity;

import java.util.List;

public class ProductCrudImpl implements CRUDManger<Product> {

    @Override
    public void create(List<Product> products) {
        try {

            products.forEach((k) -> {
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
            TypedQuery<Product> categoryTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT p FROM Product p ", Product.class);

            List<Product> categoryList = categoryTypedQuery.getResultList();
            categoryList.forEach(p1 -> System.out.printf("PRODUCT: %s, %d, [%d],CATEGORY: %s,[%d]\n",
                    p1.getName(), p1.getPrice(), p1.getId()
                    ,p1.getCategory().getName()
                    ,p1.getCategory().getId()));
            ThisEntity.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Product slectID(Long id) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            TypedQuery<Product> productTypedQuery = ThisEntity.getEntityManager()
                    .createQuery("SELECT p FROM Product p where p.id =" + id + "", Product.class);
            Product p1 = productTypedQuery.getSingleResult();
            System.out.printf("%s, %d, [%d]\n", p1.getName(), p1.getPrice(), p1.getId());
            ThisEntity.getEntityManager().getTransaction().commit();
            return p1;
        }catch (NoResultException | EntityNotFoundException e) {
            System.err.println("Id не существует");
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void update(Product product) {
        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            ThisEntity.getEntityManager().merge(product);
            ThisEntity.getEntityManager().getTransaction().commit();
            System.out.printf("Updated:[%d], %s, %d, %s\n", product.getId(),
                    product.getName(), product.getPrice(), product.getCategory().toString());

        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {

        try {
            ThisEntity.getEntityManager().getTransaction().begin();
            Product p = ThisEntity.getEntityManager().getReference(Product.class, id);
            ThisEntity.getEntityManager().remove(p);
            ThisEntity.getEntityManager().getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.err.println("Id не существует");
        } catch (Exception e) {
            ThisEntity.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

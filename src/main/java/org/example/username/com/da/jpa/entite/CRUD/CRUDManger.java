package org.example.username.com.da.jpa.entite.CRUD;

import org.example.username.com.da.jpa.entite.Category;

import java.util.List;

public interface CRUDManger<T>{
    void create(List<T> objectClass);
   void select();
   T slectID(Long id);

    void update(T objectClass);
    void delete(Long id);
}

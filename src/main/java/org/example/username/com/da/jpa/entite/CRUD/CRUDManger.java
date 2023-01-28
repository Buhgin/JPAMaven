package org.example.username.com.da.jpa.entite.CRUD;

import java.util.List;

public interface CRUDManger {
    void create(List<String> name);
   void select();
   void slectID(Long id);

    void update(Long id,String name);
    void delete(Long id);
}

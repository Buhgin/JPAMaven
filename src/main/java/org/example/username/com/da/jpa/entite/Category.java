package org.example.username.com.da.jpa.entite;

import jakarta.persistence.*;

import java.util.List;


//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy= "category",cascade = CascadeType.ALL)
    private List<Product> products;
    @OneToMany(mappedBy= "category",cascade = CascadeType.ALL)
    private  List<Option> options;
    @Column(name = "name")
    private String name;
    public Category() {

    }
    public Category(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", products=" + products +
                ", options=" + options +
                ", name='" + name + '\'' +
                '}';
    }
}

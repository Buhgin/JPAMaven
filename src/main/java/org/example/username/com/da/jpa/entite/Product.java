package org.example.username.com.da.jpa.entite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy= "product",cascade = CascadeType.ALL)
    private List<Value> values;




}

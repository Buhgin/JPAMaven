package org.example.username.com.da.jpa.entite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "values")
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="option_id")
    private Option option;

    private String value;


}

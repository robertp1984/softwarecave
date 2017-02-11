package com.example.periodictable;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PT_ELEMENT_CATEGORY")
public class ElementCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ElementCategoryGen", sequenceName = "PT_ELEMENT_CATEGORY_SEQ", allocationSize = 1)
    private int id;
    
    private String name;
    private String description;
}

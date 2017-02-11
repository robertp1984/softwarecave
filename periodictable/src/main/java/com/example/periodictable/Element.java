package com.example.periodictable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PT_ELEMENT")
@NamedQueries({
    @NamedQuery(name = "findAllElements", query = "select e from Element e"),
    @NamedQuery(name = "findElementByAtomicNumber", query = "select e from Element e where e.atomicNumber=:atomicNumber"),
    @NamedQuery(name = "findElementByAtomicNumberOrNameOrSymbol", query = "select e from Element e where e.atomicNumber=:atomicNumber or e.name=:name or e.symbol=:symbol")
})
public class Element implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ElementGen", sequenceName = "PT_ELEMENT_SEQ", allocationSize = 1)

    private int id;
    
    @Column(name = "ATOMIC_NUMBER")
    private int atomicNumber;
    private String name;
    private String symbol;
    
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private ElementCategory category;
}

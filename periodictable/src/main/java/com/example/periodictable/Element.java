package com.example.periodictable;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERIODICTABLE_ELEMENT")
@NamedQueries({
    @NamedQuery(name = "findAllElements", query = "select e from Element e"),
    @NamedQuery(name = "findElementByAtomicNumber", query = "select e from Element e where e.atomicNumber=:atomicNumber"),
    @NamedQuery(name = "findElementByAtomicNumberOrNameOrSymbol", query = "select e from Element e where e.atomicNumber=:atomicNumber or e.name=:name or e.symbol=:symbol")
})
public class Element implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int atomicNumber;
    private String name;
    private String symbol;
    
    @ManyToOne
    private ElementCategory category;
}

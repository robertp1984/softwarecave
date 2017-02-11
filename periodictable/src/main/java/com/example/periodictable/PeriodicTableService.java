package com.example.periodictable;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PeriodicTableService {

    private final Logger logger = LoggerFactory.getLogger(PeriodicTableService.class);
    
    @PersistenceContext
    private EntityManager em;
    
    
    public List<Element> getElements() {
        List<Element> list = em.createNamedQuery("findAllElements", Element.class).getResultList();
        return list;
    }
    
    public Element getElementByAtomicNumber(int number) throws ElementNotFoundException {
        try {
            Element elem = em.createNamedQuery("findElementByAtomicNumber", Element.class)
                    .setParameter("atomicNumber", number).getSingleResult();
            return elem;
        } catch (NoResultException e) {
            throw new ElementNotFoundException(String.format("Element with atomicNumber=%s not found", number), e);
        }
    }

    public Element addElement(Element element) throws ElementCategoryNotFoundException, ElementAlreadyExistsException {
        if (checkIfElementExists(element)) {
            throw new ElementAlreadyExistsException(String.format("Element already exists"));
        }
        
        int categoryId = element.getCategory().getId();
        ElementCategory category = em.find(ElementCategory.class, categoryId);
        if (category == null) {
            throw new ElementCategoryNotFoundException(String.format("Element category with id=%d not found", categoryId));
        }
        
        element.setId(0);
        element.setCategory(category);
        em.persist(element);
        return element;
    }
    
    public boolean checkIfElementExists(Element element) {
        List<Element> matchingElems = em.createNamedQuery("findElementByAtomicNumberOrNameOrSymbol", Element.class)
                .setParameter("atomicNumber", element.getAtomicNumber())
                .setParameter("name", element.getName())
                .setParameter("symbol", element.getSymbol())
                .getResultList();
        return matchingElems.size() > 0;
    }
    
    public void addElementInternal(Element element) {
        logger.info("Adding element {}", element);
        em.persist(element);
        logger.info("Added element {} with ID={}", element, element.getId());
    }

    void addElementCategory(ElementCategory category) {
        logger.info("Adding element category {}", category);
        em.persist(category);
        logger.info("Added element category {} with ID={}", category, category.getId());
    }

    public void updateElement(Element element) throws ElementCategoryNotFoundException, ElementNotFoundException {
        Element elemDb = em.find(Element.class, element.getId());
        if (elemDb == null) {
            throw new ElementNotFoundException(String.format("Element with ID=%d not found", element.getId()));
        }
        
        int categoryId = element.getCategory().getId();
        ElementCategory category = em.find(ElementCategory.class, categoryId);
        if (category == null) {
            throw new ElementCategoryNotFoundException(String.format("Element category with id=%d not found", categoryId));
        }
        
        elemDb.setAtomicNumber(element.getAtomicNumber());
        elemDb.setName(element.getName());
        elemDb.setSymbol(element.getSymbol());
        elemDb.setCategory(category);
    }

    public void removeElement(int id) throws ElementNotFoundException {
        Element element = em.find(Element.class, id);
        if (element == null) {
            throw new ElementNotFoundException(String.format("Element with ID=%d not found", id));
        }
        
        em.remove(element);
    }
}

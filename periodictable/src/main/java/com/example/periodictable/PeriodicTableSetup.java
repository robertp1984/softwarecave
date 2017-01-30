package com.example.periodictable;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PeriodicTableSetup {

    @Autowired
    private PeriodicTableService periodicTableService;
    
    private boolean isFirstSetupData = true;

    @Transactional
    @Deprecated
    public void setupData() {
        if (isFirstSetupData) {
            isFirstSetupData = false;
            addData();
        }
    }
   
    private void addData() {
        ElementCategory metalloid = new ElementCategory(0, "METALLOID", "Metalloid");
        ElementCategory otherNonMetal = new ElementCategory(0, "OTHER_NON_METAL", "Other nonmetal");
        ElementCategory halogen = new ElementCategory(0, "HALOGEN", "Halogen");
        ElementCategory nobleGas = new ElementCategory(0, "NOBLE_GAS", "Noble gas");
        ElementCategory alkaliMetal = new ElementCategory(0, "ALKALI_METAL", "Alkali metal");
        ElementCategory alkalineEarthMetal = new ElementCategory(0, "ALKALINE_EARTH_METAL", "Alkaline earth metal");
        ElementCategory lanthanoid = new ElementCategory(0, "LANTHANOID", "Lanthanoid");
        ElementCategory actinoid = new ElementCategory(0, "ACTINOID", "Actinoid");
        ElementCategory transitionMetal = new ElementCategory(0, "TRANSITION_METAL", "Transition metal");
        ElementCategory postTransitionMetal = new ElementCategory(0, "POST_TRANSITION_METAL", "Post transition metal");
        periodicTableService.addElementCategory(metalloid);
        periodicTableService.addElementCategory(otherNonMetal);
        periodicTableService.addElementCategory(halogen);
        periodicTableService.addElementCategory(nobleGas);
        periodicTableService.addElementCategory(alkaliMetal);
        periodicTableService.addElementCategory(alkalineEarthMetal);
        periodicTableService.addElementCategory(lanthanoid);
        periodicTableService.addElementCategory(actinoid);
        periodicTableService.addElementCategory(transitionMetal);
        periodicTableService.addElementCategory(postTransitionMetal);
        
        periodicTableService.addElementInternal(new Element(0, 1, "Hydrogen", "H", otherNonMetal));
        periodicTableService.addElementInternal(new Element(0, 2, "Helium", "He", nobleGas));
        periodicTableService.addElementInternal(new Element(0, 3, "Lithium", "Li", alkaliMetal));
        periodicTableService.addElementInternal(new Element(0, 4, "Beryllium", "Be", alkalineEarthMetal));
        periodicTableService.addElementInternal(new Element(0, 5, "Boron", "B", metalloid));
        periodicTableService.addElementInternal(new Element(0, 6, "Carbon", "C", otherNonMetal));
        periodicTableService.addElementInternal(new Element(0, 7, "Nitrogen", "N", otherNonMetal));
        periodicTableService.addElementInternal(new Element(0, 8, "Oxygen", "O", otherNonMetal));
        periodicTableService.addElementInternal(new Element(0, 9, "Fluorine", "F", halogen));
        periodicTableService.addElementInternal(new Element(0, 10, "Neon", "Ne", nobleGas));
    }
}

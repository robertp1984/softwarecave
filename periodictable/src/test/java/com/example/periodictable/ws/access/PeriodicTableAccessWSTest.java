
package com.example.periodictable.ws.access;

import com.example.periodictable.ElementNotFoundException;
import com.example.periodictable.PeriodicTableService;
import com.example.periodictable.ws.common.Element;
import org.dozer.DozerBeanMapper;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


public class PeriodicTableAccessWSTest {
    
    @Mock
    private PeriodicTableService periodicTableService;

    @Spy
    private DozerBeanMapper mapper;
    
    @InjectMocks
    private PeriodicTableAccessWS periodicTableWS;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetElementByAtomicNumber() throws ElementNotFoundException, ElementNotFound_Exception {
        com.example.periodictable.ElementCategory elCat = new com.example.periodictable.ElementCategory(1, "NOBLE_GAS", "Noble gas");
        com.example.periodictable.Element el = new com.example.periodictable.Element(2, 2, "", "He", elCat);

        when(periodicTableService.getElementByAtomicNumber(2)).thenReturn(el);
        
        Element element = periodicTableWS.getElementByAtomicNumber(2);
        
        verify(periodicTableService, times(1)).getElementByAtomicNumber(anyInt());
        assertEquals(element.getAtomicNumber(), 2);
    }

    @Test(expected = ElementNotFound_Exception.class)
    public void testGetElementByAtomicNumber_ElementNotFound() throws ElementNotFoundException, ElementNotFound_Exception {
        com.example.periodictable.ElementNotFoundException ex = new ElementNotFoundException("");

        when(periodicTableService.getElementByAtomicNumber(2)).thenThrow(ex);
        
        Element element = periodicTableWS.getElementByAtomicNumber(2);
    }
}

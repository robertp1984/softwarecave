package com.example.faceletscomposition;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class Presidents implements Serializable {

    private static final long serialVersionUID = 23423422384723L;
    
    private static final SelectItem[] items = {new SelectItem(0, "George Washington"), new SelectItem(1, "John Adams"),
        new SelectItem(2, "Thomas Jefferson"), new SelectItem(3, "James Madison")};
    
    private static final String[] itemDescriptions = {"George Washington was the first president of USA",
        "John Adams was the second president of USA", "Thomas Jefferson was the third president of USA",
        "James Madison was the fourth president of USA"};
    
    private int selectedIndex;

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public String getName() {
        return items[selectedIndex].getLabel();
    }

    public String getDescription() {
        return itemDescriptions[selectedIndex];
    }

    public SelectItem[] getAllItems() {
        return items;
    }
}

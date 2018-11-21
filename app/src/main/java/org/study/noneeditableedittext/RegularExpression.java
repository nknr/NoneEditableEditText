package org.study.noneeditableedittext;

public class RegularExpression {
    private String name;
    private String description;
    private boolean isSelected;

    public RegularExpression(String name, String description, boolean isSelected) {
        this.name = name;
        this.description = description;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

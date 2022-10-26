package com.sigecap.sigecapexamenbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemMenuDTO {

    private String label;
    private String icon;
    private String faIcon;
    private String link;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemMenuDTO> items;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<ItemMenuDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemMenuDTO> items) {
        this.items = items;
    }
}

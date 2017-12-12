package com.sinosoft.wateradmin.common;

import java.util.List;

/**
 * Created by lvzhixue on 2017/12/1.
 */
public class ComboTreeModel {
    private String id;
    private String text;
    private List<ComboTreeModel> children;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ComboTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<ComboTreeModel> children) {
        this.children = children;
    }
}

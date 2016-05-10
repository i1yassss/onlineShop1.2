package ru.kpfu.itis.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;


public class ProductGwt implements EntryPoint {


    public void onModuleLoad() {
        //Create an empty tab panel
        TabPanel tabPanel = new TabPanel();


        Label label2 = new Label("Create category");
        label2.setHeight("200");
        Label label3 = new Label("Back to site");
        label3.setHeight("200");

        //create titles for tabs
        String tab2Title = "Add category";

        //create tabs
        final CreateCategoryForm createCategoryForm = new CreateCategoryForm();
        tabPanel.add(createCategoryForm.getCreateCategoryForm(), tab2Title);

        //select first tab
        tabPanel.selectTab(0);

        //set width if tabpanel
        tabPanel.setWidth("800");

        tabPanel.setAnimationEnabled(true);
        // Add the widgets to the root panel.
        RootPanel.get().add(tabPanel);


    }
}
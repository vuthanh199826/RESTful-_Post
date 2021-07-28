package com.codegym.cms.formater;

import com.codegym.cms.model.Category;
import com.codegym.cms.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormater implements Formatter<Category> {
    @Autowired
     public CategoryFormater(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    ICategoryService categoryService;

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return this.categoryService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(Category object, Locale locale) {
        return null;
    }
}

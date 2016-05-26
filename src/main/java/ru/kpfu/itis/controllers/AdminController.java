package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.repository.CategoryRepository;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Qualifier("categoryRepository")
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Страница добавления категории
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add-category", method = RequestMethod.GET)
    public String addCategory(Model model) {
        return "add-category";
    }

    /**
     * Создание новой категории из админ панели
     *
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public String saveCategory(@RequestParam String name, Model model) {
        Category category = new Category();
        category.setName(HtmlUtils.htmlEscape(name));
        categoryRepository.save(category);
        return "add-category";
    }

}

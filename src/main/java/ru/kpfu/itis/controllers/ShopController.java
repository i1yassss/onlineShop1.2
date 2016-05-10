
package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.repository.GoodService;
import ru.kpfu.itis.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;


@Controller

@RequestMapping("/shop")
@SessionAttributes(types = Basket.class)

public class ShopController {

    @Qualifier("goodDAO")
    public Basket bask = null;
    @Autowired
    GoodService goodService;
    UserService userService;
    @RequestMapping(method = RequestMethod.GET)
    public String categories(ModelMap model) {
        List<Category> cats = goodService.getCategoruiesList();
        
        model.addAttribute("cats", cats);
               
        return "categories";
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String goods(
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        List<Good> goods = goodService.getList(id);
        model.addAttribute("goods", goods);
        
        return "goodlist";
    }
    
    @RequestMapping(value = "/add/{catId}/{id}", method = RequestMethod.GET)
    public String addToBasket(
            @PathVariable(value = "catId")
            Integer catId,
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        
        Basket basket = (Basket) model.get("basket");
        if (basket == null) basket = new Basket();
                
        BasketItem i = basket.get(id);
        if (i == null) {
            i = new BasketItem();
            i.setGood(goodService.find(id));
        } else {
            i.incCount();
        }

        basket.put(id, i);
        bask = new Basket();
        bask = basket;
        //model.addAttribute("basket", basket);
        model.addAttribute("basket", bask);
        return "redirect:/shop/category/" + catId.toString();
    }
    
    @RequestMapping(value = "/delete/{catId}/{id}", method = RequestMethod.GET)
    public String goodDelete(
            @PathVariable(value = "catId")
            Integer catId,
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        goodService.deleteGood(id);
        
        return "redirect:/shop/category/" + catId.toString();
    }
    
    @RequestMapping(value = "/addGood", method = RequestMethod.GET)
    public String addGood(ModelMap model){
        List<Category> cats = goodService.getCategoruiesList();
        model.addAttribute("cats", cats);
        
        return "addGood";
    }
    
    @RequestMapping(value = "/goodAddSave", method = RequestMethod.GET)
    public String goodSave(
            //@RequestParam(value = "id") Integer id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "desc") String desc, 
            @RequestParam(value = "price") Double price,
            @RequestParam(value = "cat") Integer cat,
            @RequestParam(value = "img") String image,
            ModelMap model){        
        
        model.addAttribute("name", name);
        model.addAttribute("desc", desc);
        model.addAttribute("price", price);       
        model.addAttribute("cat", cat);
        model.addAttribute("img", image);
        Good g = new Good(name, desc, price, cat, image);
        goodService.add(g);

        return "redirect:/shop/category/" + cat.toString();
    }
    
    @RequestMapping(value = "/editGood/{catId}/{id}", method = RequestMethod.GET)
    public String editGood(
            @PathVariable(value = "catId")
            Integer catId,
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        
        List<Category> cats = goodService.getCategoruiesList();
        Good g = goodService.find(id);
        model.addAttribute("catId", catId);
        model.addAttribute("cats", cats);
        model.addAttribute("good", g);
        
        return "editGood";
    }
    
    @RequestMapping(value = "/goodEditSave/{id}", method = RequestMethod.GET)
    public String userEditSave(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "desc") String desc, 
            @RequestParam(value = "price") Double price,
            @RequestParam(value = "cat") Integer cat,
            @RequestParam(value = "img") String image,
            ModelMap model) {
        model.addAttribute("id", id);

        Good g = new Good(id, name, desc, price, cat, image);
        goodService.edit(g);
        
        return "redirect:/shop/category/" + cat.toString();
    }
    
    @RequestMapping(value = "/orderAdd", method = RequestMethod.GET)
    public String addOrder(
            Integer catId,
            ModelMap model) {
        List<Good> goods = goodService.findAll();
        Timestamp date = null;
        String str = null;
        model.addAttribute("str", str);
        model.addAttribute("date", date);
        model.addAttribute("goods", goods);

        return "addOrder";
    }
    
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrder(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "DeliverAddress") String DeliverAddress,
            ModelMap model) {
        Order o = new Order();
            
        for (HashMap.Entry<Integer, BasketItem>pair:bask.entrySet()){
            Integer key = pair.getKey();
            BasketItem value = pair.getValue();
            o.setIdUser(id);
            if (!DeliverAddress.equals("")){
                o.setAddress(DeliverAddress);
            } else {
                return "addOrder";
            }
            o.setIdGood(value.getGood().getId());
            o.setAmount(value.getCount());
            o.setPrice(value.getGood().getPrice());                     
           
            goodService.addOrders(o);
        }
        Basket basket = new Basket();
        bask = new Basket();
        bask = basket;
        model.addAttribute("basket", bask);
        
        return "redirect:/user/show";
    }
    
    @RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
    public String historyOrder(
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        
        model.addAttribute("id", id);
        List<Order> o = goodService.getListOfOrderThisName(id);
        List<Good> g = goodService.findAll();
        model.addAttribute("Order", o);
        model.addAttribute("Good", g);

        return "historyOrder";
    }
    
    @RequestMapping(value = "/basketDelete", method = RequestMethod.GET)
    //@RequestMapping(value = "/basketDelete/{catId}", method = RequestMethod.GET)
    public String deleteToBasket(
            //@PathVariable(value = "catId")
            //Integer catId,
            ModelMap model) {
        
        Basket basket = new Basket();
        bask = new Basket();
        bask = basket;
        //model.addAttribute("basket", basket);
        model.addAttribute("basket", bask);
        //return "redirect:/shop/category/" + catId.toString();
        return "redirect:/shop/category/" + 1;
    }
    
    @RequestMapping(value = "/deleteGoodInBasket/{catId}/{id}", method = RequestMethod.GET)
    public String deleteToGoodBasket(
            @PathVariable(value = "catId")
            Integer catId,
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        
        Basket basket = (Basket) model.get("basket");
        if (basket == null) basket = new Basket();
               
        BasketItem i = basket.get(id);
        if (i.getCount() == 1) {
            basket.remove(id);
        } else {
            i.decCount();
        }

        bask = new Basket();
        bask = basket;
        //model.addAttribute("basket", basket);
        model.addAttribute("basket", bask);
        return "redirect:/shop/category/" + catId.toString();
    }
}

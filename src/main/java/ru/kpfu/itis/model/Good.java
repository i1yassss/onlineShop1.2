package ru.kpfu.itis.model;

import java.util.Objects;


public class Good {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer category_id;
    private String image;

    public Good() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.price);
        hash = 79 * hash + Objects.hashCode(this.category_id);
        hash = 79 * hash + Objects.hashCode(this.image);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Good other = (Good) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.category_id, other.category_id)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    public Good(String name, String description, Double price, Integer category_id, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.image = image;
    }

    public Good(Integer id, String name, String description, Double price, Integer category_id, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.image = image;
    }
    
    

    @Override
    public String toString() {
        return "Good{" + "description=" + description + ", \tname=" + name + ", \tprice=" + price + ", \tcategory_id=" + category_id +", \timage=" + image +  '}' + "\n";
    }
    
}

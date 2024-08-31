package zuzz.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @NotNull
    private long id;
    
    @Column(name = "name")
    @Size(min = 0, max = 100)
    private String name;
    
    @Column(name = "description")
    @Size(min = 0, max = 200)
    private String description;
    
    @Column(name = "stock")
    private int stock;
    
    @Column(name = "price", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @Column(name = "avaialable")
    @NotNull
    private boolean available;
    
    public Product() {
    }

    public Product(String name, String description, int stock, BigDecimal price, boolean available) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.available = available;
    }

    public Product(long id, String name, String description, int stock, BigDecimal price, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + '}';
    }
}

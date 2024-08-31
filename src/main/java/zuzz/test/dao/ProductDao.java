package zuzz.test.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import zuzz.test.model.Product;

@ApplicationScoped
public class ProductDao {
    
    @PersistenceContext(unitName = "gestionInventarioPU")
    EntityManager em;
    
    @Transactional
    public void create(Product product) {
        em.persist(product);
    }
    
    public Map<Long, Product> readAllProducts() {
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
        
        return products.stream()
                   .collect(Collectors.toMap(Product::getId,product -> product));
    }
    
    public Map<Long, Product> readAllAvailableProducts() {
        List<Product> products = em.createQuery
            ("SELECT p FROM Product p WHERE p.available = true", Product.class)
                .getResultList();
        
        return products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
    }
    
    public Map<Long, Product> readAllNotAvailableProducts() {
        List<Product> products = em.createQuery
            ("SELECT p FROM Product p WHERE p.available = false", Product.class)
                .getResultList();
        
        return products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
    }
    
    public Map<Long, Product> readAllLowStockProducts() {
        List<Product> products = em.createQuery
            ("SELECT p FROM Product p WHERE p.stock < 20", Product.class)
                .getResultList();
        
        return products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
    }
    
    public Product readOneById(long id) {
        return em.find(Product.class, id);
    }
    
    @Transactional
    public void update(long id, Product productToUpdate) {
        Product updateProduct = readOneById(id);
        if(updateProduct != null) {
            updateProduct.setName(productToUpdate.getName());
            updateProduct.setStock(productToUpdate.getStock());
            updateProduct.setPrice(productToUpdate.getPrice());
            updateProduct.setDescription(productToUpdate.getDescription());
            em.merge(updateProduct);
        }
    }
    
    @Transactional
    public void updateStock(long id, int newStock) {
        Product product = readOneById(id);
        if(product != null) {
            product.setStock(newStock);
            em.merge(product);
        }
    }
    
    @Transactional
    public void updatePrice(long id, @Digits(integer = 10, fraction = 2) BigDecimal newPrice) {
        Product product = readOneById(id);
        if(product != null) {
            product.setPrice(newPrice);
            em.merge(product);
        }
    }
    
    @Transactional
    public void updateAvailable(long id, boolean newAvailable) {
        Product product = readOneById(id);
        if(product != null && !product.isAvailable()) {
            product.setAvailable(newAvailable);
            em.merge(product);
        }
    }
    
    @Transactional
    public boolean delete(long id) {
        Product deleteProduct = readOneById(id);
        if(deleteProduct != null) {
            em.remove(deleteProduct);
            return true;
        }
        return false;
    }
}

package zuzz.test.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import zuzz.test.model.Product;

@ApplicationScoped
public class ProductDao {
    
    @PersistenceContext(unitName = "gestionInventarioPU")
    EntityManager em;
    
    @Transactional
    public void create(Product product) {
        em.persist(product);
    }
    
    public List<Product> readAllProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
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
        if(product != null)
            em.merge(product);
    }
    
    @Transactional
    public void delete(long id) {
        Product deleteProduct = readOneById(id);
        if(deleteProduct != null)
            em.remove(deleteProduct);
    }
}

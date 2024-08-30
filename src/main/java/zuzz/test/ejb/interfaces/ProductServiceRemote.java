package zuzz.test.ejb.interfaces;

import jakarta.ejb.Remote;
import jakarta.validation.constraints.Digits;
import java.util.Map;
import zuzz.test.model.Product;

@Remote
public interface ProductServiceRemote {
    
    Map<Long, Product> getAllProducts();
    
    Map<Long, Product> getAllAvailableProducts();
    
    Map<Long, Product> getAllNotAvailableProducts();
    
    Map<Long, Product> getAllLowStockProducts();
    
    Product getProductById(long id);
    
    void createNewProduct(Product product);
    
    void deleteProduct(long id);
    
    void updateProduct(long id, Product productToUpdate);
    
    void updateStock(long id, int newStock);
    
    void updatePrice(long id, @Digits(integer = 6, fraction = 2) double price);
    
    void updateStateOfProduct(long id, boolean newState);
}

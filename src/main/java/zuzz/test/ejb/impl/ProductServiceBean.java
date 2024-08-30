package zuzz.test.ejb.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.Map;
import zuzz.test.dao.ProductDao;
import zuzz.test.ejb.interfaces.ProductServiceRemote;
import zuzz.test.model.Product;

@Stateless
public class ProductServiceBean implements ProductServiceRemote {

    @Inject
    private ProductDao productDao;
    
    @Override
    public Map<Long, Product> getAllProducts() {
        return productDao.readAllProducts();
    }
    
    @Override
    public Map<Long, Product> getAllAvailableProducts() {
        return productDao.readAllAvailableProducts();
    }
    
    @Override
    public Map<Long, Product> getAllNotAvailableProducts() {
        return productDao.readAllNotAvailableProducts();
    }
    
    @Override
    public Map<Long, Product> getAllLowStockProducts() {
        return productDao.readAllLowStockProducts();
    }

    @Override
    public Product getProductById(long id) {
        return productDao.readOneById(id);
    }

    @Override
    public void createNewProduct(Product product) {
        productDao.create(product);
    }

    @Override
    public void deleteProduct(long id) {
        productDao.delete(id);
    }

    @Override
    public void updateProduct(long id, Product productToUpdate) {
        productDao.update(id, productToUpdate);
    }

    @Override
    public void updateStock(long id, int newStock) {
        productDao.updateStock(id, newStock);
    }

    @Override
    public void updatePrice(long id, double price) {
        productDao.updatePrice(id, price);
    }
    
    @Override
    public void updateStateOfProduct(long id, boolean newState) {
        productDao.updateAvailable(id, newState);
    }
}

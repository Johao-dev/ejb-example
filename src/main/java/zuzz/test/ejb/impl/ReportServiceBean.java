package zuzz.test.ejb.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;
import zuzz.test.ejb.interfaces.ProductServiceRemote;
import zuzz.test.ejb.interfaces.ReportServiceRemote;
import zuzz.test.model.Product;

@Singleton
public class ReportServiceBean implements ReportServiceRemote {

    @EJB
    private ProductServiceRemote productServiceBean;
    
    private Map<String, Object> report = new HashMap<>();
    
    Map<Long, Product> availableProducts = new HashMap<>();
    Map<Long, Product> notAvailableProducts = new HashMap<>();
    Map<Long, Product> lowStockProducts = new HashMap<>();

    @PostConstruct
    public void init() {
        availableProducts = productServiceBean.getAllAvailableProducts();
        notAvailableProducts = productServiceBean.getAllNotAvailableProducts();
        lowStockProducts = productServiceBean.getAllLowStockProducts();
    }
    
    @Override
    @Lock(LockType.READ)
    public Map<String, Object> generateReport() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            report.put("avaiableProducts", objectMapper.writeValueAsString(this.availableProducts));
            report.put("notAvaiableProducts", objectMapper.writeValueAsString(this.notAvailableProducts));
            report.put("lowStockProducts", objectMapper.writeValueAsString(this.lowStockProducts));
        } catch(JsonProcessingException ex) {
            ex.printStackTrace(System.out);
        }
        return report;
    }
}

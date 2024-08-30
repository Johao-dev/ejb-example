package zuzz.test.ejb.interfaces;

import jakarta.ejb.Remote;
import java.util.Map;

@Remote
public interface ReportServiceRemote {
    
    Map<String, Object> generateReport();
}

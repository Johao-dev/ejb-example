package zuzz.test.controller;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import zuzz.test.ejb.interfaces.ProductServiceRemote;
import zuzz.test.ejb.interfaces.ReportServiceRemote;
import zuzz.test.model.Product;

@Path("/products")
public class ProductController {
    
    @EJB
    private ProductServiceRemote productServiceBean;
    
    @EJB
    private ReportServiceRemote reportServiceBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        try {
            Map<Long, Product> products = productServiceBean.getAllProducts();
            return Response
                    .ok(products)
                    .build();
        } catch(Exception ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        try {
            productServiceBean.createNewProduct(product);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } catch(Exception ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Long id) {
        try {
            Product product = productServiceBean.getProductById(id);
            if(product != null)
                return Response
                        .ok(product)
                        .build();
            else
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        } catch(Exception ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Long id, Product product) {
        try {
            productServiceBean.updateProduct(id, product);
            return Response
                    .ok(product)
                    .build();
        } catch(Exception ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") Long id) {
        try {
            boolean deleted = productServiceBean.deleteProduct(id);
            if(deleted)
                return Response
                        .noContent()
                        .build();
            else
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        } catch(Exception ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateReport() {
        try {
            Map<String, Object> report = reportServiceBean.generateReport();
            return Response
                    .ok(report)
                    .build();
        } catch(Exception ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
}

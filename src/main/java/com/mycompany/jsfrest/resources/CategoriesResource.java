package com.mycompany.jsfrest.resources;

import com.mycompany.jsfrest.dao.DaoException;
import com.mycompany.jsfrest.dao.CategoryDAO;
import com.mycompany.jsfrest.entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author cristiane
 */
@Path("/categories")
public class CategoriesResource implements Serializable{
    
    private static final long serialVersionUID = -645545444545554509L;
	
    private Category category = new Category();
    private List<Category> categories;
    private int id;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    	
    public CategoriesResource() {
            CategoryDAO categoryDAO = new CategoryDAO();
            this.categories = categoryDAO.selectAll();
    }
	
    @GET
    @Produces({"application/json"})
    public Response index() throws DaoException {
            return Response.ok(this.categories).build();
    }
}

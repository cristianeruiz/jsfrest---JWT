package com.mycompany.jsfrest.resources;

import com.mycompany.jsfrest.dao.ItemDAO;
import com.mycompany.jsfrest.dao.DaoException;
import com.mycompany.jsfrest.entity.Item;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author cristiane
 */
@Path("/items")
public class ItemsResource implements Serializable{
    
    private static final long serialVersionUID = -645545444545554509L;
	
    //private Item item = new Item();
    private List<Item> items;
    private int id;
    
    /*public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }*/

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
	
    public ItemsResource() {
            ItemDAO itemDAO = new ItemDAO();
            this.items = itemDAO.selectAll();
    }
	
    @GET
    @Produces({"application/json"})
    public Response index() throws DaoException {
            return Response.ok(this.items).build();
    }

    @Path("/{id}")
    @Produces({"application/json"})
    @GET
    public Response show(@PathParam("id") Integer id) throws DaoException {
        ItemDAO itemDAO = new ItemDAO();
        return Response.ok(itemDAO.selectById(id)).build();
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response create(Item item) throws DaoException {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.insert(item);
        this.items = itemDAO.selectAll();
        return Response.ok(this.items).build();
    }
    
    @Path("/{id}")
    @PUT
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response update(@PathParam("id") Integer id, Item item) throws DaoException {
        ItemDAO itemDAO = new ItemDAO();
        //item = itemDAO.selectById(id);
        item.setId(id);
        itemDAO.update(item);
	this.items = itemDAO.selectAll();        
        return Response.ok(this.items).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces({"application/json"})
    public Response destroy(@PathParam("id") Integer id) throws DaoException {
            
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.delete(id);
        this.items = itemDAO.selectAll();
        return Response.ok().build();
    }
}

package de.themole.stocker.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.TypedQuery;
import de.themole.stocker.StockerConfiguration;
import de.themole.stocker.adapters.ItemTypeAdapter;
import de.themole.stocker.dao.Item;

/**
 * Servlet implementation class Items
 */
@WebServlet(urlPatterns = {"/items"})
public class ItemsService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = StockerConfiguration.getEntitiyManager();
		// Add parameter and a limited query to only fetch parts of the items 
		TypedQuery<Item> q = (TypedQuery<Item>) em.createNamedQuery("Item.findAll", Item.class);
		List<Item> rl = q.getResultList();
		if (rl.size() > 0) {
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(Item.class, new ItemTypeAdapter())
					.serializeNulls()
					.setPrettyPrinting()
					.create();
			response.setContentType("application/json");
			String jsonstr = gson.toJson(rl);
			response.getWriter().append(jsonstr);
		} else {
			response.sendError(HttpServletResponse.SC_NO_CONTENT, "No items found for your query.");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

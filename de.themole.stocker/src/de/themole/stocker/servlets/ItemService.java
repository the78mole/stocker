package de.themole.stocker.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.themole.stocker.StockerConfiguration;
import de.themole.stocker.adapters.ItemTypeAdapter;
import de.themole.stocker.dao.Item;

/**
 * Servlet implementation class ItemService
 */
@WebServlet(urlPatterns = { "/item"})
public class ItemService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemService() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		EntityManager em = StockerConfiguration.getEntitiyManager();
		BufferedReader br = new BufferedReader(
				new InputStreamReader(request.getInputStream()));
				
		StringBuilder sb = new StringBuilder();
		
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			sb.append(line);
		}

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Item.class, new ItemTypeAdapter())
				.create();
		
		Item i = gson.fromJson(sb.toString(), Item.class);
		
		if (i.getId() != null) {
			response.sendError(HttpServletResponse.SC_CONFLICT, 
					"A new item can not contain an ID (Primary Key).");
		} else {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(i);
			t.commit();
			
			response.setContentType("application/json");
			response.getWriter().append(gson.toJson(i));
		}
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

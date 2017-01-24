package monitoring;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimulateurServlet")
/**
 * Servlet pour gérer la création de pannes (créée par l'utilisateur ou aléatoire)
 * @author Adrien Guillaud-Rollin & Christophe Thao Ky 
 *
 */
public class SimulateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SimulateurServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess (request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recuperation des variables lors du post du formulaire de la page jsp.
		String name = request.getParameter("Nom");
		String idMachine = request.getParameter("idMachine");
		int TypeMachine = Integer.parseInt(request.getParameter("Type"));
		
		String TypeMachin = "undefined";
		
		PanneDAO panneService = new PanneDAOImpl();
		
		//Mise en forme des valeurs recuperees avant saisie dans la base de donnees.
		switch(TypeMachine){
		case 1 : TypeMachin = "Serveur"; 
				switch(idMachine.length()){
					case 1 : idMachine = "AAAAAAAAAAAA000"+idMachine; break;
					case 2 : idMachine = "AAAAAAAAAAAA00"+idMachine; break;
					case 3 : idMachine = "AAAAAAAAAAAA0"+idMachine; break;
					case 4 : idMachine = "AAAAAAAAAAAA"+idMachine; break;
				}
			break;
		case 2 : TypeMachin = "Pare-feu"; 
				switch(idMachine.length()){
					case 1 : idMachine = "BBBBBBBBBBBBBB0"+idMachine; break;
					case 2 : idMachine = "BBBBBBBBBBBBBB"+idMachine; break;
				}
			break;
		case 3 : TypeMachin = "Routeur"; 
				switch(idMachine.length()){
					case 1 : idMachine = "CCCCCCCCCCCCC00"+idMachine; break;
					case 2 : idMachine = "CCCCCCCCCCCCC0"+idMachine; break;
					case 3 : idMachine = "CCCCCCCCCCCCC"+idMachine; break;
				}
			break;
		}
		try{
			panneService.insertNewPanne(idMachine, name, TypeMachin);
		} catch (SQLException e ){
			
		}
		
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String pageName = "/Simulateur.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

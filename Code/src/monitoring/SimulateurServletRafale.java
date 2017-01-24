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

@WebServlet("/SimulateurServletRafale")
/**
 * Servlet pour créer des pannes en rafales
 * @author Adrien Guillaud-Rollin & Christophe Thao Ky 
 *
 */
public class SimulateurServletRafale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SimulateurServletRafale() {
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
		int NombrePannes = Integer.parseInt(request.getParameter("NombrePannes"));
		PanneDAO panneService = new PanneDAOImpl();
		for(int i = 0;i<NombrePannes;i++){
			String Types[] = {"Serveur", "Pare-feu", "Routeur"};
			String NomPanne[] = {"Reseau", "Crash disque", "Probleme memoire"};
			
			int choixType = (int) Math.floor(Math.random() * 3);
			
			
			String TypeChoisi = Types[choixType];
			String NomPanneChoisi = "";
			int idM;
			String idMachine = "";
			
			
			if(TypeChoisi.equals("Serveur")){
				int choixNomPanne = (int) Math.floor(Math.random() * 3);
				NomPanneChoisi = NomPanne[choixNomPanne];
				idM = (int) Math.floor(Math.random() * 1999)+1;
				idMachine = String.valueOf(idM);
				switch(idMachine.length()){
					case 1 : idMachine = "AAAAAAAAAAAA000"+idMachine; break;
					case 2 : idMachine = "AAAAAAAAAAAA00"+idMachine; break;
					case 3 : idMachine = "AAAAAAAAAAAA0"+idMachine; break;
					case 4 : idMachine = "AAAAAAAAAAAA"+idMachine; break;
				}
			}
			
			if(TypeChoisi.equals("Pare-feu")){
				NomPanneChoisi = "Reseau";
				idM = (int) Math.floor(Math.random() * 49)+1;
				idMachine = String.valueOf(idM);
				switch(idMachine.length()){
					case 1 : idMachine = "BBBBBBBBBBBBBB0"+idMachine; break;
					case 2 : idMachine = "BBBBBBBBBBBBBB"+idMachine; break;
				}
			}
			
			if(TypeChoisi.equals("Routeur")){
				NomPanneChoisi = "Reseau";
				idM = (int) Math.floor(Math.random() * 99)+1;
				idMachine = String.valueOf(idM);
				switch(idMachine.length()){
					case 1 : idMachine = "CCCCCCCCCCCCC00"+idMachine; break;
					case 2 : idMachine = "CCCCCCCCCCCCC0"+idMachine; break;
					case 3 : idMachine = "CCCCCCCCCCCCC"+idMachine; break;
				}
			}

			//insertion de la panne
			try {
				panneService.insertNewPanne(idMachine, NomPanneChoisi, TypeChoisi);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

package monitoring;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MonitoringServlet")
/**
 * Servlet de la console de panne
 * @author Marie Biau-Guilhembet & Anne-Charline Baclet
 *
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		try {
			doProcess(request,response);
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String pageName = "/Monitoring.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
		String type = request.getParameter("type");
		String typeMachine = request.getParameter("typeMachine");
		PanneDAO panneService = new PanneDAOImpl();
		List<Panne> liste;
		
		int nbPannesMin = panneService.getNbPannesMin();
		int nbPannesHeure = panneService.getNbPannesHeure();
		int nbPannesJour = panneService.getNbPannesJour();
		int nbPannesMois = panneService.getNbPannesMois();
		
		request.setAttribute("nbPannesMin", nbPannesMin);
		request.setAttribute("nbPannesHeure", nbPannesHeure);
		request.setAttribute("nbPannesJour", nbPannesJour);
		request.setAttribute("nbPannesMois", nbPannesMois);
		if(typeMachine==null){
			if(type == null){
				liste = panneService.getAll();
			}else if(type.equals("minute")){
				liste = panneService.getPannesByMinute();
			}else if(type.equals("heure")){
				liste = panneService.getPannesByHeure();
			}else if(type.equals("jour")){
				liste = panneService.getPannesByJour();
			}else if(type.equals("mois")){
				liste = panneService.getPannesByMois();
			}else{
				liste = panneService.getAll();
			}
		}else if(typeMachine.equals("serveur")){
			if(type == null){
				liste = panneService.getPanneByTypeMachine(typeMachine);
			}else if(type.equals("minute")){
				liste = panneService.getPannesByMinute(typeMachine);
			}else if(type.equals("heure")){
				liste = panneService.getPannesByHeure(typeMachine);
			}else if(type.equals("jour")){
				liste = panneService.getPannesByJour(typeMachine);
			}else if(type.equals("mois")){
				liste = panneService.getPannesByMois(typeMachine);
			}else{
				liste = panneService.getPanneByTypeMachine(typeMachine);
			}
		}else if(typeMachine.equals("routeur")){
			if(type == null){
				liste = panneService.getPanneByTypeMachine(typeMachine);
			}else if(type.equals("minute")){
				liste = panneService.getPannesByMinute(typeMachine);
			}else if(type.equals("heure")){
				liste = panneService.getPannesByHeure(typeMachine);
			}else if(type.equals("jour")){
				liste = panneService.getPannesByJour(typeMachine);
			}else if(type.equals("mois")){
				liste = panneService.getPannesByMois(typeMachine);
			}else{
				liste = panneService.getPanneByTypeMachine(typeMachine);
			}
		}else if(typeMachine.equals("Pare-feu")){
			if(type == null){
				liste = panneService.getPanneByTypeMachine(typeMachine);
			}else if(type.equals("minute")){
				liste = panneService.getPannesByMinute(typeMachine);
			}else if(type.equals("heure")){
				liste = panneService.getPannesByHeure(typeMachine);
			}else if(type.equals("jour")){
				liste = panneService.getPannesByJour(typeMachine);
			}else if(type.equals("mois")){
				liste = panneService.getPannesByMois(typeMachine);
			}else{
				liste = panneService.getPanneByTypeMachine(typeMachine);
			}
		}else{
			if(type == null){
				liste = panneService.getAll();
			}else if(type.equals("minute")){
				liste = panneService.getPannesByMinute(typeMachine);
			}else if(type.equals("heure")){
				liste = panneService.getPannesByHeure(typeMachine);
			}else if(type.equals("jour")){
				liste = panneService.getPannesByJour(typeMachine);
			}else if(type.equals("mois")){
				liste = panneService.getPannesByMois(typeMachine);
			}else{
				liste = panneService.getAll();
			}
		}
		
		request.setAttribute("listePannes", liste);

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

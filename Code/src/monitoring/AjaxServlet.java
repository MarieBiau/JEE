package monitoring;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AjaxServlet")
/**
 * Servlet utilisé pour les requêtes AJAX vers le serveur
 * @author Pierre-Antoyne Fontaine
 *
 */
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
		// response.getWriter().append("<message>TEST</message>");
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PanneDAO panneService = new PanneDAOImpl();
		String requestType = request.getParameter("requestType");
		if(requestType!=null){
			if(requestType.equals("getPannes")){
				response.setContentType("text/xml");
				String type = request.getParameter("type");
				String typeMachine = request.getParameter("typeMachine");
				String XMLPannes = new String();
				try{
					List<Panne> listePannes = panneService.getAll();
					if(typeMachine==null){
						if(type == null){
							listePannes = panneService.getAll();
						}else if(type.equals("minute")){
							listePannes = panneService.getPannesByMinute();
						}else if(type.equals("heure")){
							listePannes = panneService.getPannesByHeure();
						}else if(type.equals("jour")){
							listePannes = panneService.getPannesByJour();
						}else if(type.equals("mois")){
							listePannes = panneService.getPannesByMois();
						}else{
							listePannes = panneService.getAll();
						}
					}else if(typeMachine.equals("serveur")){
						if(type == null){
							listePannes = panneService.getPanneByTypeMachine(typeMachine);
						}else if(type.equals("minute")){
							listePannes = panneService.getPannesByMinute(typeMachine);
						}else if(type.equals("heure")){
							listePannes = panneService.getPannesByHeure(typeMachine);
						}else if(type.equals("jour")){
							listePannes = panneService.getPannesByJour(typeMachine);
						}else if(type.equals("mois")){
							listePannes = panneService.getPannesByMois(typeMachine);
						}else{
							listePannes = panneService.getPanneByTypeMachine(typeMachine);
						}
					}else if(typeMachine.equals("routeur")){
						if(type == null){
							listePannes = panneService.getPanneByTypeMachine(typeMachine);
						}else if(type.equals("minute")){
							listePannes = panneService.getPannesByMinute(typeMachine);
						}else if(type.equals("heure")){
							listePannes = panneService.getPannesByHeure(typeMachine);
						}else if(type.equals("jour")){
							listePannes = panneService.getPannesByJour(typeMachine);
						}else if(type.equals("mois")){
							listePannes = panneService.getPannesByMois(typeMachine);
						}else{
							listePannes = panneService.getPanneByTypeMachine(typeMachine);
						}
					}else if(typeMachine.equals("Pare-feu")){
						if(type == null){
							listePannes = panneService.getPanneByTypeMachine(typeMachine);
						}else if(type.equals("minute")){
							listePannes = panneService.getPannesByMinute(typeMachine);
						}else if(type.equals("heure")){
							listePannes = panneService.getPannesByHeure(typeMachine);
						}else if(type.equals("jour")){
							listePannes = panneService.getPannesByJour(typeMachine);
						}else if(type.equals("mois")){
							listePannes = panneService.getPannesByMois(typeMachine);
						}else{
							listePannes = panneService.getPanneByTypeMachine(typeMachine);
						}
					}else{
						if(type == null){
							listePannes = panneService.getAll();
						}else if(type.equals("minute")){
							listePannes = panneService.getPannesByMinute(typeMachine);
						}else if(type.equals("heure")){
							listePannes = panneService.getPannesByHeure(typeMachine);
						}else if(type.equals("jour")){
							listePannes = panneService.getPannesByJour(typeMachine);
						}else if(type.equals("mois")){
							listePannes = panneService.getPannesByMois(typeMachine);
						}else{
							listePannes = panneService.getAll();
						}
					}
					XMLPannes += "<pannes>";
					for(Panne p: listePannes){
						XMLPannes += "<panne>";
						XMLPannes += "<idMachine>"+p.getIdMachine()+"</idMachine>";
						XMLPannes += "<nomPanne>"+p.getName()+"</nomPanne>";
						XMLPannes += "<typeMachine>"+p.getTypeMachine()+"</typeMachine>";
						XMLPannes += "<date>"+p.getDate()+"</date>";
						XMLPannes += "<resolue>"+p.isResolue()+"</resolue>";
						XMLPannes += "</panne>";		
					}
					XMLPannes += "</pannes>";		
					response.getWriter().append(XMLPannes);

				} catch(SQLException e){
					e.printStackTrace();
				}
			} else if(requestType.equals("setChecked")){
				response.setContentType("text/text");
				boolean resolue = Boolean.parseBoolean(request.getParameter("checked"));
				int idPanne = Integer.parseInt(request.getParameter("idPanne"));
				
				try {
					panneService.updatePanne(idPanne, resolue);
					response.getWriter().append("réussie");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}

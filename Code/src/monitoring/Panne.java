package monitoring;

/**
 * Classe représentant une panne
 * @author Adam Ouechani
 *
 */
public class Panne {
	private int idPanne;
	private String name;
	private String idMachine;
	private String typeMachine;
	private String date;
	private boolean resolue;
	
	public Panne (int _idPanne,String _name, String _idMachine, String _typeMachine, String _date,boolean _resolue){
		this.idPanne = _idPanne;
		this.name = _name;
		this.idMachine = _idMachine;
		this.typeMachine = _typeMachine;
		this.date = _date;
		this.resolue = _resolue;
	}

	@Override
	public String toString() {
		return "Panne [name=" + name + ", idMachine=" + idMachine
				+ ", typeMachine=" + typeMachine + ", date=" + date + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdMachine() {
		return idMachine;
	}

	public void setIdMachine(String idMachine) {
		this.idMachine = idMachine;
	}

	public String getTypeMachine() {
		return typeMachine;
	}

	public void setTypeMachine(String typeMachine) {
		this.typeMachine = typeMachine;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isResolue() {
		return resolue;
	}

	public void setResolue(boolean resolue) {
		this.resolue = resolue;
	}

	public int getIdPanne() {
		return idPanne;
	}

	public void setIdPanne(int idPanne) {
		this.idPanne = idPanne;
	}
	
}

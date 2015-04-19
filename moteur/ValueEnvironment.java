package moteur;

import java.awt.Point;
import java.util.HashMap;

import controleur.ControleurAvance;
import controleur.ControleurPinceau;
import controleur.ControleurTourne;

public class ValueEnvironment extends HashMap<String, Integer> {
	private String couleur;
	private ControleurAvance avance;
	private ControleurPinceau pinceau;
	private int taille;
	private int angleActuel;
	private boolean valPinceau;
	private boolean modeSansFENETRE;
	private String nom;
	
	public boolean isValPinceau() {
		return valPinceau;
	}
	public ControleurPinceau getPinceau() {
		return pinceau;
	}
	public void setPinceau(ControleurPinceau pinceau) {
		this.pinceau = pinceau;
	}
	private boolean encour=false;
	private Point positionActuel;
	
	
	public boolean isEncour() {
		return encour;
	}
	public void setEncour(boolean encour) {
		this.encour = encour;
	}
	public ValueEnvironment() {
		
		super();
		this.setModeSansFENETRE(false);
		this.valPinceau=false;
		this.angleActuel=90;
		this.couleur="BLACK";
		this.setTaille(10);
		this.setPositionActuel(new Point(0,0));
	}
	public void addVariable(String name) 
	throws Exception {
		this.put(name, null);
	}
	public void setVariable(String name, int value) 
	throws Exception {
		this.put(name, value);
	}
	public Integer getValue(String name){
		return this.get(name);
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public ControleurAvance getAvance() {
		return avance;
	}
	public void setAvance(ControleurAvance avance) {
		this.avance = avance;
	}
	public Point getPositionActuel() {
		return positionActuel;
	}
	public void setPositionActuel(Point positionActuel) {
		this.positionActuel = positionActuel;
	}
	public void setValPinceau(boolean descendre) {
		this.valPinceau=descendre;
		
		if(this.pinceau!=null){
			this.pinceau.action(descendre);
		}
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public int getAngleActuel() {
		return angleActuel;
	}
	public void setAngleActuel(int angleActuel) {
		this.angleActuel = angleActuel;
	}
	public boolean isModeSansFENETRE() {
		return modeSansFENETRE;
	}
	public void setModeSansFENETRE(boolean modeSansFENETRE) {
		this.modeSansFENETRE = modeSansFENETRE;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}

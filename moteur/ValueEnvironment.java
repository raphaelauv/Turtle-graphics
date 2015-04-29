package moteur;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

import controleur.GUI.ControleurAvance;
import controleur.GUI.ControleurCouleur;
import controleur.GUI.ControleurDimension;
import controleur.GUI.ControleurEpaisseur;
import controleur.GUI.ControleurPinceau;
import controleur.GUI.ControleurTourne;

public class ValueEnvironment extends HashMap<String, Integer> {
	private String couleur;
	private ControleurAvance avance;
	private ControleurPinceau controleurPinceau;
	private ControleurCouleur controleurCouleur;
	private ControleurTourne controleurTourne;
	private ControleurDimension controleurDimension;
	private ControleurEpaisseur controleurEpaisseur;
	private int taille;
	private int angleActuel;
	private boolean valPinceau;//true en BAS , false en Haut
	private boolean modeSansFENETRE;
	private String nom;
	private Point positionActuel;
	
	
	public ValueEnvironment(boolean valPinceau , int angleActuel, String couleur , int epaisseur) {
		super();
		this.setModeSansFENETRE(false);
		this.valPinceau=valPinceau;
		this.angleActuel=angleActuel;
		this.couleur=couleur;
		this.setTaille(epaisseur);
		this.setPositionActuel(new Point(0,0));
		
	}
	public ControleurPinceau getPinceau() {
		return controleurPinceau;
	}
	public void setPinceau(ControleurPinceau pinceau) {
		this.controleurPinceau = pinceau;
	}

	public void addVariable(String name) throws Exception {
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
		if(this.getControleurCouleur()!=null){
			this.getControleurCouleur().action(this.couleur);
		}
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
	public boolean isValPinceau() {
		return valPinceau;
	}
	public void setValPinceau(boolean descendre) {
		this.valPinceau=descendre;
		if(this.controleurPinceau!=null){
			this.controleurPinceau.action(descendre);
		}
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
		if(this.controleurEpaisseur!=null){
			this.controleurEpaisseur.action(taille);
		}
	}
	public int getAngleActuel() {
		return angleActuel;
	}
	public void setAngleActuel(int angleActuel) {
		this.angleActuel = angleActuel;
		
    	if(this.getControleurTourne()!=null){
    		this.getControleurTourne().action(angleActuel);
		}
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
	public ControleurTourne getControleurTourne() {
		return controleurTourne;
	}
	public void setControleurTourne(ControleurTourne controleurTourne) {
		this.controleurTourne = controleurTourne;
	}
	public ControleurCouleur getControleurCouleur() {
		return this.controleurCouleur;
	}
	public void setControleurCouleur(ControleurCouleur controleurCouleur) {
		this.controleurCouleur=controleurCouleur;
	}
	public ControleurDimension getControleurDimension() {
		return controleurDimension;
	}
	public void setControleurDimension(ControleurDimension controleurDimension) {
		this.controleurDimension = controleurDimension;
	}
	public ControleurEpaisseur getControleurEpaisseur() {
		return this.controleurEpaisseur;
	}
	public void  setControleurEpaisseur(ControleurEpaisseur controleurEpaisseur) {
		this.controleurEpaisseur=controleurEpaisseur;
	}
}
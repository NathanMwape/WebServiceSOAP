package service;

import metier.Connexion;
import metier.EsisDb;
import metier.Etudiant;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Name;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebService(name = "ArchiveWs")
public class Serveur {

    Connection conn = Connexion.getConneXion();
    @WebMethod(operationName = "connexion")
    public Etudiant connecterUser(@WebParam(name = "login") String login, @WebParam(name = "password") String password){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Etudiant etudiant = null;
        try {
            String sql = "SELECT * FROM etudiant WHERE login = ? AND passwords = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idEtudiant");
                String nom = rs.getString("nomEtudiant");
                Date dateNaissance = rs.getDate("dateNaissance");
                etudiant = new Etudiant(id, nom, login, password, dateNaissance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }
    @WebMethod(operationName = "listeEtudiant")
    public List<Etudiant> getListEtudiants(){
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        try {
            String sql = "SELECT * FROM etudiant";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idEtudiant");
                String nom = rs.getString("nomEtudiant");
                String login = rs.getString("login");
                String password = rs.getString("passwords");
                Date dateNaissance = rs.getDate("dateNaissance");
                Etudiant etudiant = new Etudiant(id, nom, login, password, dateNaissance);
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }


    @WebMethod(operationName = "SupprimerService")
    public void supprimerEtudiant(@WebParam(name = "id") int id){
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM etudiant WHERE idEtudiant = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @WebMethod(operationName = "ModifierService")
    public void modifierEtudiant(@WebParam(name = "id") int id,@WebParam(name = "etudiant") Etudiant etudiant) {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE etudiant SET nomEtudiant = ?, login = ?, passwords = ?, dateNaissance = ? WHERE idEtudiant = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, etudiant.getNomEtudiant());
            stmt.setString(2, etudiant.getLogin());
            stmt.setString(3, etudiant.getPassword());
            stmt.setDate(4, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @WebMethod(operationName = "ajouterService")
    public void ajouterEtudiant(@WebParam(name = "Etudiant") Etudiant etudiant){
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO etudiant (nomEtudiant, login, passwords, dateNaissance) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, etudiant.getNomEtudiant());
            stmt.setString(2, etudiant.getLogin());
            stmt.setString(3, etudiant.getPassword());
            stmt.setDate(4, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @WebMethod(operationName = "rechercherService")
    public List<Etudiant> rechercherEtudiantParNom(@WebParam(name = "nom") String nom) {
        List<Etudiant> etudiants = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM etudiant WHERE nomEtudiant LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nom + "%");
            // Exécuter la requête SQL
            rs = stmt.executeQuery();
            // Parcourir les résultats de la requête et créer les objets Etudiant correspondants
            while (rs.next()) {
                int id = rs.getInt("idEtudiant");
                String nomEtudiant = rs.getString("nomEtudiant");
                String login = rs.getString("login");
                String password = rs.getString("passwords");
                Date dateNaissance = rs.getDate("dateNaissance");
                Etudiant etudiant = new Etudiant(id, nomEtudiant, login, password, dateNaissance);
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.ApiRedSocial;
import entidades.JSONResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Conexion;

/**
 *
 * @author USUARIO
 */
public class ApiRedSocialDAO implements IDAO<ApiRedSocial>{
    
    private final JSONResponse json;
    private final Conexion cnx;
    private ResultSet rs;
    private PreparedStatement ps;
    private int registros = 0;
    
    public ApiRedSocialDAO() {
        json = new JSONResponse();
        cnx = new Conexion();
    }

    @Override
    public JSONResponse insert(ApiRedSocial obj) {
        String sql = "INSERT INTO tbl_info_redes_sociales(`red_social`, `informacion`) VALUES (?,?)";
        
        try {
            int index = 0;
            ps = cnx.getConexion().prepareStatement(sql);
            ps.setString(++index, obj.getRedSocial());
            ps.setString(++index, obj.getInformacion());

            registros = ps.executeUpdate();
            if (registros > 0) {
                json.setCodRespuesta("000");
                json.setMenRespuesta("PROCESO EXITOSO");
            } else {
                json.setCodRespuesta("099");
                json.setMenRespuesta("ERROR DE EJECUCION DE SQL");
            }
        } catch (SQLException e) {
            json.setCodRespuesta("009");
            json.setMenRespuesta("Error al ejecutar el insert");
            System.out.println("USUARIO_DAO - insert :Error al ejecutar el insert");
        } finally {
            try {
                ps.close();
                cnx.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return json;
        
    }

    @Override
    public JSONResponse selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONResponse select(List<String> condicion) {
        String sql = "SELECT informacion FROM tbl_info_redes_sociales WHERE red_social = ? order by id desc limit 1";
        String informacion = "";
        try {
            ps = cnx.getConexion().prepareStatement(sql);
            ps.setString(1, condicion.get(0));
            rs = ps.executeQuery();
            while (rs.next()) {
                informacion = rs.getString("informacion");
            }
            if (informacion != null && !informacion.isEmpty()) {
                json.setCodRespuesta("000");
                json.setMenRespuesta("PROCESO EXITOSO");
            } else {
                json.setCodRespuesta("002");
                json.setMenRespuesta("NO EXISTE EL USUARIO");
            }

            json.setData(informacion);

        } catch (SQLException e) {
            json.setCodRespuesta("009");
            json.setMenRespuesta("Error al consultar la informacion");
            System.out.println("USUARIO_DAO - select :Error al consultar la informacion");
        } finally {
            try {
                rs.close();
                ps.close();
                cnx.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return json;
        
    }

    @Override
    public JSONResponse update(ApiRedSocial obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONResponse delete(int identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ApiRedSocialDAO;
import entidades.ApiRedSocial;
import entidades.JSONResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class CtrlApiRedSocial {
    
    public JSONResponse createRegistro(String redSocial, String informacion) {
        ApiRedSocial api = new ApiRedSocial();
        api.setRedSocial(redSocial);
        api.setInformacion(informacion);
        
        ApiRedSocialDAO dao = new ApiRedSocialDAO();
        JSONResponse json = dao.insert(api);
        return json;
    } 
    
    public JSONResponse getLastInformacion( String redSocial ) {
        List<String> condicion = new ArrayList<>();
        condicion.add(redSocial);
        
        ApiRedSocialDAO api = new ApiRedSocialDAO();
        return api.select(condicion);
    }
    
}

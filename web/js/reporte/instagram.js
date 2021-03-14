/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {


    $("#btn_instagram").click(function () {
        instagramGetInformacion();
    });

});

function saveInstagramInformacion(informacion) {

    $.ajax({
        type: "POST",
        dataType: "json",
//            url: "webresources/redSocial/prueba?test=Marcelo"
        url: "webresources/redSocial/create?redSocial=Instagram&informacion=" + informacion
//            url: "http://localhost:8080/ApiRestUniversidad/webresources/universidad/getUsuario?usuario="+usuario+"&contrasenia="+pass
    }).done(function (data, textStatus, jqXHR) {
        if (data.codRespuesta === "000") {
            swal("Good job!", data.menRespuesta, "success");
        } else {
            swal("Error de Server!", data.menRespuesta, "error");
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        swal("Error", textStatus + ' - ' + errorThrown, "error");
    });
}

function instagramGetInformacion() {

    // se tiene que registrar en esta paquina para poder hacer la consulta al api
    // y teien que elegir el plan gratis. pero tiene un número limitado de consulta.
    // y solo tiene que copiar la parte del settings para que funcione.
    //https://rapidapi.com/yuananf/api/instagram28?endpoint=apiendpoint_98d05d19-e64a-411d-8ed5-413627593bb5
    
//https://developers.facebook.com/docs/instagram-basic-display-api/getting-started
    //https://instafeedjs.com/#/
    //
    
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "https://instagram28.p.rapidapi.com/medias?user_id=25025320&next_cursor=QVFDQjY1YkdMY0x0YTFEdWo4R21CNFVuMUV0WmpIdUdlWVNTaXY4VXlVdkYyZEhJT2tBay1aRDN4OWpUMFZHbG1KNmpiMnQ2Z09FVjFhWHUxTDBfNDh5Yg%3D%3D&batch_size=20",
        "method": "GET",
        "headers": {
            "x-rapidapi-key": "02a4cb7162msh5b8cc620cd68be8p1a1779jsn647a1f835630",
            "x-rapidapi-host": "instagram28.p.rapidapi.com"
        }
    };

    $.ajax(settings).done(function (response) {

        var obj = JSON.parse(response);
        $("#p_status").text(obj.status);
        $('#json-renderer').jsonViewer(obj);
        if (obj.status === 'ok') {
//            var user = obj.data;
//            var data = JSON.stringify(user.user.edge_owner_to_timeline_media.edges);
//            var informacion = JSON.stringify(obj.data.user.edge_owner_to_timeline_media.edges);
//            debugger;
//            console.log(informacion);
            saveInstagramInformacion(obj);
        }
    });

//    mostrarInformacion();

}

function mostrarInformacion() {

    $.getJSON("webresources/redSocial/searchLast?redSocial=Instagram", function (data) {
        
//        var items = [];
        var datos = JSON.stringify(data.data);
        console.log(datos);
        $('#json-renderer').jsonViewer(data.data);
        /*$.each(datos, function (key, val) {
            items.push("<li id='" + key + "'>" + val + "</li>");
        });

        $("<ul/>", {
            "class": "my-new-list",
            html: items.join("")
        }).appendTo("body");*/
    });

}

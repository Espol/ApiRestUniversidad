/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var scopes = 'public_profile,email';
var statusChangeCallback = function (response, callback) {
//  		console.log(response);

    if (response.status === 'connected') {
        getFacebookData();
    } else {
        callback(false);
    }
};
var checkLoginState = function (callback) {
    FB.getLoginStatus(function (response) {
        callback(response);
    });
};
var getFacebookData = function () {
    FB.api('/me', 'GET', {}, function (response) {//obtiene los datos del usuario login
        console.log(response);
        $("#name").text(response.name);
        $("#id_name").text(response.id);
        var informacion = JSON.stringify(response);
        saveFacaBookInformacion( informacion );
    });
};
var facebookLogin = function () {
    checkLoginState(function (data) {
        console.log(data);
        if (data.status !== 'connected') {
            FB.login(function (response) {//realiza el login de facebook
                if (response.status === 'connected')
                    getFacebookData();
            }, {scope: scopes});// los permisos
        }
    });
};

var getFacebookDataLike = function () {
    FB.api('/me/likes', 'GET', {}, function (response) {
        console.log(response.data);
        $('#tb_facebook_likes').DataTable( {
        data: response.data,
        columns: [
            { title: "name" , data: "name" },
            { title: "id", data: "id" },
            { title: "created_time", data: "created_time" }
        ]
    } );
    var informacion = JSON.stringify(response);
    saveFacaBookInformacion( informacion );
    });
};

var fecaBookGetLike = function () {
    checkLoginState(function (data) {
        if (data.status === 'connected') {
            getFacebookDataLike();
//            FB.login(function (response) {//realiza el login de facebook
//                if (response.status === 'connected')
//                    getFacebookDataLike();
//            }, {scope: scopes});// los permisos
        }
    });
};

function saveFacaBookInformacion( informacion ) {
    
    $.ajax({
            type: "POST",
            dataType: "json",
//            url: "webresources/redSocial/prueba?test=Marcelo"
            url: "webresources/redSocial/create?redSocial=facebook&informacion="+informacion
//            url: "http://localhost:8080/ApiRestUniversidad/webresources/universidad/getUsuario?usuario="+usuario+"&contrasenia="+pass
        }).done(function (data, textStatus, jqXHR) {
            console.log(data);
            if(data.codRespuesta === "000"){
                swal("Good job!", data.menRespuesta, "success");
            }else{
                swal("Error de Server", data.menRespuesta, "error");
            }
        }).fail(function (jqXHR, textStatus, errorThrown) {
            swal("Error", textStatus, "error");
        });
}

$(document).ready(function () {
    FB.init({
        appId: '123104449738799',
        xfbml: true,
        version: 'v10.0'
    });
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response, function () {
        });
    });

    $(document).on('click', '#id_facebook', function (e) {
        facebookLogin();
    });
    
    $("#btn_like").click( function() {
        console.log('evento click');
        fecaBookGetLike();
    });

});

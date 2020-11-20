//jquery murio gracias a react, angular, vue

$(function(){
    //evento el boton
    $("#cifrar").on("click", function(){
        var elhtml = $("#elhtml").val();
        var clave = $("#clave").val();
        //proceso
        var encriptar = CryptoJS.AES.encrypt(elhtml, clave).toString();
        //vamos a mostrarlo
        $("#cifrado").val(encriptar);
        console.log(encriptar, clave);
    });
});
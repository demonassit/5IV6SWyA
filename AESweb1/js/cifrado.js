//necesitamos variables

var cadena = "habia una vez un patito bien linda y kawaii y un dia se cayo en una coladera y chillo";
var clave = "essecretaessecretaessecretaessecreta";

//procedimiento

var cifrar = CryptoJS.AES.encrypt(cadena, clave);

var descifrar = CryptoJS.AES.decrypt(cifrar, clave);

document.getElementById("demo0").innerHTML = cadena;
document.getElementById("demo1").innerHTML = cifrar;
document.getElementById("demo2").innerHTML = descifrar;
document.getElementById("demo3").innerHTML = descifrar.toString(CryptoJS.enc.Utf8);
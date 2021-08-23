
/*MESSAGGIO ERRORE EMAIL ESISTENTE*/
$(window).ready(function(){
	if($(".email-error").text() != ""){
	$(".email-error").css("display","block");
}
});


/*EYE PASSWORD*/
$("#eye-icon").click(function(){
	
	 $("#eye-icon").toggleClass("fa-eye-slash");
        if($("#input-password").attr("type") == "text"){
            $("#input-password").attr("type","password");
        }
        else{
            $("#input-password").attr("type","text");
        }
	
});

/*FORM VALIDATOR*/

    $("#form").validate({

        rules: {

            email: { required: true, email: true },
            password: {required: true,minlength: 5,maxlength: 10},
            name: {required: true},
            lastname: {required: true},
            telephone: {required: true},
        },

        messages: {
            email:{
                required:  "Inserire l'email",
                email: "Inserire un' email valida"
            },
            password:{
                required: "Inserire la password",
                minlength: "Inserire almeno 5 caratteri",
                maxlength: "Inserire al massimo 10 caratteri"
            },
            telephone:{
                required: "Inserire il numero di telefono"
            },
            name:{
                required: "Inserire il nome"
            },
            lastname:{
                required: "Inserire il cognome"
            }
        }        
    });
    
/*MENU ICON*/
$("#menu-icon").click(function(){
	$("#menu-icon").children().first().toggleClass(" fa-times");
	$(".menu-navbar").toggleClass("active")});


/*PROFILO - ABILITA MODIFICA PRENOTAZIONE */

/* 1) Leggo il testo dei vari span -> funzione each() (saranno stringhe in formato dd - MM - yyyy)
	2) Converto i valori letti in Date (occhio al formato da trasformare in yyyy/MM/dd)
	3) Salvo i valori convertiti in un array 
	4) Salvo in un array i bottoni associati (i due array avranno la stessa dimensione (ogni prenotazione ha un bottone))
	5) Ciclo for (se la data in posizione i è minore della data di oggi -> MOSTRA BOTTONE ASSOCIATO (stesso indice))
*/

function stringToDateConverter(s){
	var parts = s.split("-");
	return new Date(parts[2], parts[1] - 1, parts[0]);
}

var datePrenotazioni = [];
$(".data-prenotazione").each(function(){
	datePrenotazioni.push(stringToDateConverter($(this).text()));
});



var currentDate = new Date(); //restituisce data corrente
var bottoniModifica = $(".modifica-prenotazione");
var bottoniElimina = $(".elimina-prenotazione");
console.log(bottoniElimina);

for(var i = 0; i < datePrenotazioni.length; i++){
	
	var bottoneModificaAssociato = bottoniModifica[i];
	var bottoneEliminaAssociato = bottoniElimina[i];
	
	
	if(currentDate < datePrenotazioni[i]){
		$(bottoneModificaAssociato).css("display", "inline-block");
		$(bottoneEliminaAssociato).css("display", "inline-block");
		
	}

}

/* PROFILO - ELIMINA UTENTE */

$("#elimina-utente-btn, .esc").click(function(){
	$(".modifica-ruolo").removeClass("disabled");
	$(".profilo, .modifica-ruolo").toggleClass("disabled");
	$(".conferma-elimina").toggleClass("active");
	
    
});

/* PROFILO - ELIMINA PRENOTAZIONE 

$("#elimina-prenotazione-btn").click(function(){
	var idPrenotazione = $(".elimina-prenotazione-btn").attr("value");
	console.log(idPrenotazione);
	$(".profilo").toggleClass("disabled");
	$(".conferma-elimina").toggleClass("active");
	$(".popup-text").text("Sei sicuro di voler eliminare la prenotazione?");
	$(".elimina-utente-btn").html('<a class="conferma-elimina-utente-si grow" th:href="@{/prenotazioni/eliminaPrenotazione(id="+idPrenotazione+")}"><span>SI</span></a>');
	
})

*/



/*PROFILO - MODIFICA BOTTONI SU MOBILE*/



if(window.innerWidth < 737){
	$(".elimina").html('<i class="far fa-trash-alt"></i>');
	$(".modifica").html('<i class="far fa-edit"></i>');
	$(".dettagli-prenotazione").html('<i class="fas fa-search"></i>');
	
	
}

/*UTENTI PAGE*/

/*RIMUOVERE ROLE_ DA RUOLO*/

console.log($(".sezione-ruoli"));
$(".sezione-ruoli").each(function(){

	var ruolo = $(this).children("#ruolo").attr("value").split("_");
	$(this).children("#ruolo").text(ruolo[1]);
});



/*NASCONDERE FILTRI SU SCHERMO PICCOLO*/

var windowWidth = window.innerWidth;
var text = $(".open-filter").text();
console.log(text);


windowWidth < 737 ? $(".filtri-ricerca").css("display","none") : null;
windowWidth > 737 ? $(".open-filter").css("display","none") : null;

/*APRIRE FILTRI SU SCHERMO PICCOLO*/

$(".open-filter").click(function(){
	$(".filtri-ricerca").toggleClass("active");
	$(this).text($(this).text() == 'Nascondi' ? text : 'Nascondi');
})




/*MODIFICARE VIDEO HEADER*/

var loginSection = document.querySelector(".login");
var loginIntroSection = loginSection.querySelector(".intro");
loginIntroSection.firstElementChild.innerHTML += '<div class="filter"></div>';
var loginVideo = loginIntroSection.querySelector("video");
var loginTitleSection = loginIntroSection.querySelector(".title");
var loginBlockquoteSection = loginIntroSection.querySelector("blockquote");

$(loginVideo).attr("src", "../resources/login-video.mp4");
$(loginBlockquoteSection).text("Accedi");
$(loginBlockquoteSection).css("font-size","3rem");
$(loginTitleSection).css("filter","brightness(1.3)");










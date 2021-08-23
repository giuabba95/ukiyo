$("#foto-pietanza").change(function(){
		console.log("CAMBIATO");
		$(".anteprima-foto-box").css("display","block");
		mostraImmagineAnteprima(this);
	
	});
	
function mostraImmagineAnteprima(fileInput){
	file = fileInput.files[0];
	reader = new FileReader();
	
	reader.onload = function(e) {
		$("#anteprima-foto-pietanza").attr('src', e.target.result);
		console.log(e.target.result);
	};
	
	reader.readAsDataURL(file);
}

$("#aggiungi-categoria-link, .esc").click(function(){
	$(".aggiungi-categoria").toggleClass("active");
	$(".form").toggleClass("disabled");
});



/*ARROTONDARE PREZZO PRODOTTI*/



$(".prezzo").each(function(){
		var prezzo = $(this).text();
		if(prezzo.split(".")[1] == 0){
		$(this).text(prezzo.split(".")[0]);
}

})



/*DETTAGLI HOVER SU CARD PRODOTTO*/
$(".prodotto").hover(function(){
	$(this).children(".filter").toggleClass("active");
});

/*NASCONDERE FILTRI SU SCHERMO PICCOLO

var menuWindowWidth = window.innerWidth;

menuWindowWidth < 737 ? $(".filtri-ricerca").css("display","none") : null;


APRIRE FILTRI SU SCHERMO PICCOLO

$(".open-filter").click(function(){
	$(".filtri-ricerca").toggleClass("active");
	var text = $(this).text();
	$(this).text(text == 'Ricerca un prodotto' ? 'Nascondi' : 'Ricerca un prodotto');
})

*/

/*MODIFICARE VIDEO HEADER*/

var menuSection = document.querySelector(".menu");
var menuIntroSection = menuSection.querySelector(".intro");
menuIntroSection.firstElementChild.innerHTML += '<div class="filter"></div>';
var menuVideo = menuIntroSection.querySelector("video");
var menuTitleSection = menuIntroSection.querySelector(".title");
var menuBlockquoteSection = menuIntroSection.querySelector("blockquote");

$(menuVideo).attr("src", "../resources/menu-video.mp4");
$(menuBlockquoteSection).text("Il nostro menu");
$(menuTitleSection).css("filter","brightness(1.3)");





	
/*MIN TODAY SU INPUT DATE*/

$(window).ready(function() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; //January is 0!
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}

	today = yyyy + '-' + mm + '-' + dd;
	$(".calendario").attr("min", today);

});

/*NASCONDERE FILTRI SU SCHERMO PICCOLO*/

var windowWidth = window.innerWidth;
var text = $(".open-filter").text();
console.log(text);


windowWidth < 737 ? $(".filtri-ricerca").css("display", "none") : null;
windowWidth > 737 ? $(".open-filter").css("display", "none") : null;

/*APRIRE FILTRI SU SCHERMO PICCOLO*/

$(".open-filter").click(function() {
	$(".filtri-ricerca").toggleClass("active");
	$(this).text($(this).text() == 'Nascondi' ? text : 'Nascondi');
})


/*SPOSTARE PRODOTTI CHECKED NEL CARRELLO*/

	


	$(".prodotto").children(".nome").click(function() {
	
		var prezzo = $(this).siblings().children(".prezzo").text();
		console.log(prezzo);
		/*$(".carrello").children(".prodotti-carrello").append('<div class="prodotto-carrello"><label>' + $(this).text() + '</label><input type="hidden" name="pietanze" value='+ $(this).next().text()><span>€</span><span>'+ prezzo +'</span><span><i class="far fa-trash-alt trash"></i></span></div>').addClass("prodotto-aggiunto");*/
		
		$('<div class="prodotto-carrello"><label class="nome-prodotto-carrello" style="flex-basis: 50%; font-family: Roboto; margin: 0">' + $(this).text() + '</label><input type="hidden" name="pietanza" th:field="*{pietanza}" value='+ $(this).next().text() +' ><div><span>€</span><span class="prezzo-carrello">'+ prezzo +'</span></div><span><i class="far fa-trash-alt trash"></i></span></div>').addClass("prodotto-aggiunto")
			.css({ 'display': 'flex',
					'justify-content': 'space-between',
					'align-items': 'center',
					
					'padding': '15px 0 5px 0'
			})
			.appendTo($(".prodotti-carrello"));
		var somma = parseInt($(".totale").text());
		somma += parseInt(prezzo) ;
		
		$(".totale").text(somma);
		
		
		
		});
		
		
		$(".prodotto-carrello").on(click, function(event){
		
			alert("Ciao");
			
			$(this).remove();
			
		});
		
	
		
	
		



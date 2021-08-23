



const header = document.querySelector(".intro");
const title = header.querySelector(".title");
const image = header.querySelector("img");

var controller = new ScrollMagic.Controller();

var sceneLuckyCat = new ScrollMagic.Scene({
    duration: 1,
    triggerElement: header,
    triggerHook: 0
})
.setPin(header)
.addTo(controller);

var pinHeader = window.innerWidth > 737 ? ".sticky" : false;
var topscroll = window.innerWidth < 737 ? -20 : -30;
var durata = window.innerWidth < 737 ? 1 : 3;

gsap.timeline({
    scrollTrigger:{
        trigger: header,
        start: "2% top",
        duration: ".sticky",
        pin: pinHeader,
        toggleActions: "play none reverse reset",
        end: "+=100"
    }
    
})
.to(title, 1, {opacity: 1, y: topscroll})
.to(".title-line", 1, {width: "100%"});


  







let accelamount = 0.1;
let scrollpos = 0;
let delay = 0;


/*sceneLuckyCat.on('update', e => {
    scrollpos = e.scrollPos;
    console.log(scrollpos);
    if(scrollpos < 899){
        image.src="../resources/lucky-cat/lucky-cat" + Math.round(scrollpos) + ".jpg";
    }
    
});*/


/*var presentationTitleScene = new ScrollMagic.Scene({
    duration: 500,
    triggerElement: "#trigger1",
    triggerHook: 0.5
})
.setTween(gsap.to(presentationDescription, 3, {opacity: 1}))
.addIndicators({name: "pres"})
.addTo(controller);*/

/*gsap.to(presentationTitle, {
    scrollTrigger : {
      trigger: "#trigger1",
      start: "top top",
      toggleActions: "play none none reverse"
    },
    
    opacity: 1,
    duration: 1
  });*/
  
  var startAnimation = window.innerWidth < 737 ? "top center" : "top 30%";
  

  gsap.timeline({
    scrollTrigger : {
      trigger: ".presentation",
      start: startAnimation,
      toggleActions: "play none none none",
      
    }
  })
  .from(".presentation-title", .5, {opacity: 0})
  .to(".presentation-title", 1, {opacity: 1})
  .from("#trigger2",.5,{opacity: 0})
  .to("#trigger2",1,{opacity: 1})


  /*gsap.to(presentationDescription, {
    scrollTrigger : {
      trigger: "#trigger2",
      start: "60% center",
      toggleActions: "play none none reset",
      pin: presentation,
    },
    
    opacity: 1,
    duration: 1
  });
  */





gsap.registerPlugin(ScrollTrigger);

/*gsap.to(presentationTitle, {opacity: 1, duration: 2, delay: 0.3});
gsap.to(presentationDescription, {opacity: 1, duration: 2, delay: 0.5});*/


gsap.to(".panel", {
  yPercent: -100, 
  ease: "none",
  stagger: 0.5,
  scrollTrigger: {
    trigger: "#container",
    start: "top top",
    end: "+=300%",
    scrub: true,
    pin: true
  }
});


gsap.set(".panel", {zIndex: (i, target, targets) => targets.length - i});

gsap.timeline({
    scrollTrigger: {
      trigger: ".deconstruction2",
      start: "bottom top",
      toggleActions: "play none none none",
      duration: 900,
     

    }
   
  })
  .from(".img-container", 1, {opacity: 0})
  .to(".img-container", 2, {opacity: 1})
  .to("#sushi-riceball", 1, {yPercent: 30}, 0)
  .to("#nigiri-shrimp", 2, { yPercent:  -95}, 0)
  
  


  gsap.to("#sushi-set",{
      scrollTrigger: {
          trigger: ".sushi-img",
          start: "top center",
          toggleActions: "play none none reverse",
        },
        right: 0,
        duration: 3
  })

const blockquote = document.querySelector("#quote");
const bar = blockquote.querySelector("span");

var startBoxFoto = window.innerWidth < 737 ? "top 80%" : window.innerWidth > 737 && window.innerWidth < 968 ? "top 70%": "top top";
var pinBoxFoto = window.innderWidth > 737 ? ".box-foto" : false;
console.log(pinBoxFoto);

gsap.timeline({
    scrollTrigger:{
        trigger:".box-foto",
        start: startBoxFoto,
        duration: 3,
        pin: pinBoxFoto,
        toggleActions: "play none none none",
        
    }
})
.from("#box", 1, {opacity: 0, scale: 0})
.to("#box", .5, {left: "15%", scale: 1.3, borderColor: '#D5C897', borderWidth: 6, boxShadow: '1px 1px 0px 0px rgba(84,78,59,0.09)'})
.from(blockquote, 1.5, {x:200, opacity: 0})
.from(bar, 1, {width: 0}, "=-.5")
.from("#salmon", 1, {x:-200, opacity: 0 }, "=-.1")
.from("#shrimps", 1, {x:200, opacity: 0 }, "=-.7")


const compositionBlockquote = document.querySelector("#quote2");
const compositionBar = compositionBlockquote.querySelector(".bar");
const compositionImages = document.querySelector(".composition-images") ;
const compositionImg1 = compositionImages.querySelector(".composition-images img:nth-child(1)");
const compositionImg2 = compositionImages.querySelector(".composition-images img:nth-child(2)");
const compositionImg3 = compositionImages.querySelector(".composition-images img:nth-child(3)");

var startComposition = window.innerWidth < 737 ? "top top" : "top 60%";
var pinComposition = window.innerWidth > 737 ? ".composition" : false;

gsap.timeline({
    scrollTrigger: {
        trigger: ".composition",
        start: startComposition,
        toggleActions: "play none none none",
        end: "+=100",
        
        
        
    }
})
.to(compositionImg1, 1, {width: "100%",opacity: 1})
.to(compositionImg2, 1, {width: "100%",opacity: 1})
.to(compositionImg3, 1, {width: "100%",opacity: 1})
.to(compositionBlockquote,.5,{opacity: 1})
.to(compositionBar, {width: "100%", duration: 2.5});


/*HORIZONTAL CAROUSEL*/

if(window.innerWidth > 737){

console.log("SONO NELL IF");

const horizontalCarousel = document.querySelector(".horizontal-carousel");
const horizontalCarouselImages = horizontalCarousel.querySelectorAll(".horizontal-carousel-img");
const horizontalCarouselText = horizontalCarousel.querySelectorAll(".horizontal-carousel-i");
const horizontalCarouselI = horizontalCarousel.querySelectorAll("i");
const horizontalCarouselBar = horizontalCarousel.querySelectorAll(".bar");


gsap.timeline({
    scrollTrigger: {
        trigger : horizontalCarousel,
        start : "top 30%",
        toggleActions: "play none none none",
    }
})
.to(horizontalCarouselImages[1], .3, {"margin-top": "0px"})
.to(horizontalCarouselText[0] , .6, {bottom: "20%"})
.to(horizontalCarouselI[0], .3, {"opacity": 1})
.to(horizontalCarouselBar[0], .3, {width: "100%"})
.to(horizontalCarouselImages[0], .3, {"margin-top": "50px"})
.to(horizontalCarouselImages[2], .3, {"margin-top": "50px"})
.to(horizontalCarouselImages[3], .3, {"margin-top": "20%"})
.to(horizontalCarouselText[1] , .6, {top: "-10%"})
.to(horizontalCarouselI[1], .3, {"opacity": 1})
.to(horizontalCarouselBar[1], .3, {"margin-bottom": 20, width: "100%"})

}


gsap.timeline({
	scrollTrigger:{
	trigger: ".presentation",
	start: "bottom center"
	}
})
.from(".call-to-action", 1, {"width": "0%"})
.to(".call-to-action", 1, {"width": "100%"})
.from(".call-to-action>div", .5, {"opacity": 0})
.to(".call-to-action>div", .5, {"opacity": 1});




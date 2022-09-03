let animado = document.querySelectorAll(".animado");
let animadoIzquierda = document.querySelectorAll(".animadoIzquierda");
let animadoDerecha = document.querySelectorAll(".animadoDerecha");

function mostrarScrollArriba(){
    let scrollTop = document.documentElement.scrollTop;
    for(var i = 0; i < animado.length; i++){
        let alturaAnmiado = animado[i].offsetTop;
        if(alturaAnmiado - 500 < scrollTop){
            animado[i].style.opacity = 1;
            animado[i].classList.add("mostrarArriba");
        }
    }
}

function mostrarScrollIzquierda(){
    let scrollTop = document.documentElement.scrollTop;
    for(var i = 0; i < animadoIzquierda.length; i++){
        let alturaAnmiado = animadoIzquierda[i].offsetTop;
        if(alturaAnmiado - 500 < scrollTop){
            animadoIzquierda[i].style.opacity = 1;
            animadoIzquierda[i].classList.add("mostrarIzquierda");
        }
    }
}

function mostrarScrollDerecha(){
    let scrollTop = document.documentElement.scrollTop;
    for(var i = 0; i < animadoDerecha.length; i++){
        let alturaAnimado = animadoDerecha[i].offsetTop;
        if(alturaAnimado - 500 < scrollTop){
            animadoDerecha[i].style.opacity = 1;
            animadoDerecha[i].classList.add("mostrarDerecha");
        }
    }
}

window.addEventListener("scroll", mostrarScrollArriba);
window.addEventListener("scroll", mostrarScrollIzquierda);
window.addEventListener("scroll", mostrarScrollDerecha);
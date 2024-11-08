
function ocultarMensaje() {
    const mensaje = document.querySelector('.mensajeExito');
    if (mensaje) {
        mensaje.style.display = 'none';
    }
}

function openModal() {
    document.querySelector('.modal-overlay').classList.add('active');
    const carrousel = document.querySelector('.carrusel-container');
    carrousel.style.display = "none";
}
function closeModal() {
    document.querySelector('.modal-overlay').classList.remove('active');
    const carrousel = document.querySelector('.carrusel-container');
    carrousel.style.display = "";
}
document.querySelector('.modal-overlay').addEventListener('click', (e) => {
    if (e.target === document.querySelector('.contenedorForm')) {
        closeModal();
    }
});

document.addEventListener('DOMContentLoaded', function () {
    let currentIndex = 0;
    const images = document.querySelectorAll('.imagen');
    const totalImages = images.length;

    function showNextImage() {
        images[currentIndex].classList.remove('active');
        
        currentIndex = (currentIndex + 1) % totalImages; // Vuelve a 0 al llegar al final
        
        images[currentIndex].classList.add('active');
    }

    setInterval(showNextImage, 4000);
});
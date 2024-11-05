


function ocultarMensaje() {
    const mensaje = document.querySelector('.mensajeExito');
    if (mensaje) {
        mensaje.style.display = 'none';
    }
}

function openModal() {
    document.querySelector('.modal-overlay').classList.add('active');
}
function closeModal() {
    document.querySelector('.modal-overlay').classList.remove('active');
}
document.querySelector('.modal-overlay').addEventListener('click', (e) => {
    if (e.target === document.querySelector('.modal-overlay')) {
        closeModal();
    }
});

function openModal2() {
    document.querySelector('.modal-overlay2').classList.add('active');
}
function closeModal2() {
    document.querySelector('.modal-overlay2').classList.remove('active');
}
document.querySelector('.modal-overlay2').addEventListener('click', (e) => {
    if (e.target === document.querySelector('.modal-overlay2')) {
        closeModal2();
    }
});
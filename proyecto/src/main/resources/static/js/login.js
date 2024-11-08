// Mostrar contraseña
document.getElementById('showPassword').addEventListener('change', function() {
    const passwordField = document.getElementById('password');
    passwordField.type = this.checked ? 'text' : 'password';
});

<<<<<<< HEAD
// Validación de campos vacíos
document.querySelector('.loginForm').addEventListener('submit', function(event) {
    const email = document.getElementById('email').value.trim();  // Asegúrate de que el ID sea 'email'
    const password = document.getElementById('password').value.trim();
    const errorMessage = document.getElementById('errorMessage');
    
    if (!email || !password) {
        event.preventDefault();  // Evitar envío del formulario
        errorMessage.style.display = 'block';
    } else {
        errorMessage.style.display = 'none';
    }
});

// script.js

// Obtener el botón que abre el modal y la superposición
const openModalBtn = document.getElementById('openModalBtn');
const loginOverlay = document.querySelector('.login-overlay');

// Función para abrir el modal
function openModal() {
    loginOverlay.classList.add('active');
}

// Función para cerrar el modal (puedes agregar un botón de cerrar o cerrar al hacer clic fuera)
function closeModal() {
    loginOverlay.classList.remove('active');
}

// Agregar el evento al botón de abrir el modal
openModalBtn.addEventListener('click', openModal);

// Si deseas cerrar el modal al hacer clic fuera de él (en el overlay), agrega este evento:
loginOverlay.addEventListener('click', (event) => {
    if (event.target === loginOverlay) {
        closeModal();
    }
});
=======

>>>>>>> origin/main


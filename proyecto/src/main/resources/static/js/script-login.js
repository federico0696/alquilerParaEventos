document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("loginForm");
    const usernameInput = document.getElementById("username");
    const passwordInput = document.getElementById("password");
    const showPasswordCheckbox = document.getElementById("showPassword");
    const errorMessage = document.getElementById("errorMessage");

    // Validación del formulario
    loginForm.addEventListener("submit", function(event) {
        // Evitar el envío si los campos están vacíos
        if (usernameInput.value.trim() === "" || passwordInput.value.trim() === "") {
            event.preventDefault(); // Evita el envío del formulario
            errorMessage.style.display = "block"; // Muestra mensaje de error
        } else {
            errorMessage.style.display = "none"; // Oculta mensaje de error si todo está bien
        }
    });

    // Mostrar/Ocultar contraseña
    showPasswordCheckbox.addEventListener("change", function() {
        passwordInput.type = showPasswordCheckbox.checked ? "text" : "password";
    });
});

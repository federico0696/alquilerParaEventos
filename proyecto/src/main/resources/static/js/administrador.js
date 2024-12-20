////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONFIRMACION ADICIONAL DE ELIMINACIÓN DE UN USUARIO
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".btn-eliminar").forEach(button => {
        button.addEventListener("click", event => {
            const confirmDelete = confirm("¿Estás seguro de que deseas eliminar?");
            if (!confirmDelete) {
                event.preventDefault();
            }
        });
    });
});

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
// FILTRO BUSQUEDA USUARIOS

document.addEventListener("DOMContentLoaded", () => {
    const filtros = [
        { inputId: "filtroPropietarios", tablaSelector: "section:nth-of-type(1) tbody tr" },
        { inputId: "filtroInquilinos", tablaSelector: "section:nth-of-type(2) tbody tr" },
    ];

    filtros.forEach(({ inputId, tablaSelector }) => {
        const filtroInput = document.getElementById(inputId);
        const filas = document.querySelectorAll(tablaSelector);

        filtroInput.addEventListener("input", () => {
            const query = filtroInput.value.toLowerCase();
            filas.forEach(fila => {
                const email = fila.children[1].textContent.toLowerCase();
                fila.style.display = email.includes(query) ? "" : "none";
            });
        });
    });
});

// FILTRO BUSQUEDA INMUBLES (Diego- Agregar)

document.addEventListener("DOMContentLoaded", () => {
    const filtros1 = [
        { inputId: "filtroInmuebles", tablaSelector: "section:nth-of-type(1) tbody tr" },
    ];

    filtros1.forEach(({ inputId, tablaSelector }) => {
        const filtroInput = document.getElementById(inputId);
        const filas = document.querySelectorAll(tablaSelector);

        filtroInput.addEventListener("input", () => {
            const query = filtroInput.value.toLowerCase();
            filas.forEach(fila => {
                const localidad = fila.children[2].textContent.toLowerCase();
                fila.style.display = localidad.includes(query) ? "" : "none";
            });
        });
    });
});
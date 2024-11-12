document.getElementById('archivo').addEventListener('change', function() {
    document.getElementById('miFormulario').submit();
});

document.querySelector('.bi-feather').addEventListener('click', function() {
    const input = document.querySelector('#nombre');
    const boton = document.querySelector('#botonNombre');
    if (input.style.display === 'none' || input.style.display === '') {
        input.style.display = 'block';
        boton.style.display = 'block';
    } else {
        input.style.display = 'none';
        boton.style.display = 'none';
    }
});

document.querySelector('.editDomicilio').addEventListener('click', function() {
    const input = document.querySelector('#domicilio');
    const boton = document.querySelector('#botonDomicilio');
    if (input.style.display === 'none' || input.style.display === '') {
        input.style.display = 'block';
        boton.style.display = 'block';
    } else {
        input.style.display = 'none';
        boton.style.display = 'none';
    }
});

document.querySelector('.editEmailPropietario').addEventListener('click', function() {
    const input = document.querySelector('#emailPropietario');
    const boton = document.querySelector('#botonEmailPropietario');
    if (input.style.display === 'none' || input.style.display === '') {
        input.style.display = 'block';
        boton.style.display = 'block';
    } else {
        input.style.display = 'none';
        boton.style.display = 'none';
    }
});

document.querySelector('.editEmailInquilino').addEventListener('click', function() {
    const input = document.querySelector('#emailInquilino');
    const boton = document.querySelector('#botonEmailInquilino');
    if (input.style.display === 'none' || input.style.display === '') {
        input.style.display = 'block';
        boton.style.display = 'block';
    } else {
        input.style.display = 'none';
        boton.style.display = 'none';
    }
});
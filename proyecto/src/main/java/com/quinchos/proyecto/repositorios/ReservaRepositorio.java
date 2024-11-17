package com.quinchos.proyecto.repositorios;

import com.quinchos.proyecto.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    // Métodos personalizados si los necesitas
}


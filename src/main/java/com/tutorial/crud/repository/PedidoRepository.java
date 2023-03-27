package com.tutorial.crud.repository;

import com.tutorial.crud.dto.PedidoDto;
import com.tutorial.crud.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


}

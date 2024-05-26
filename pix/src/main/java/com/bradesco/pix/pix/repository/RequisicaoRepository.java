package com.bradesco.pix.pix.repository;

import com.bradesco.pix.pix.domain.RequisicaoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoRepository extends JpaRepository<RequisicaoPagamento, Integer> {
}

package com.bradesco.pix.pix.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("pix")
    private String pix;

    @JsonProperty("nomeProduto")
    private String nomeProduto;

    @JsonProperty("codigoPedido")
    private String codigoPedido;

    @JsonProperty("precoProduto")
    private Double precoProduto;

    @JsonProperty("statusPagamento")
    private StatusPagamento statusPagamento;
}

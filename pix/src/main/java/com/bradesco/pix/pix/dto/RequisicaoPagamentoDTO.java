package com.bradesco.pix.pix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoPagamentoDTO {

    private String pix;

    private String nomeProduto;

    private String codigoPedido;

    private Double precoProduto;

}

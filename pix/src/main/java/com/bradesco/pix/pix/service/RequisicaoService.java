package com.bradesco.pix.pix.service;

import com.bradesco.pix.pix.domain.RequisicaoPagamento;
import com.bradesco.pix.pix.domain.StatusPagamento;
import com.bradesco.pix.pix.dto.RequisicaoPagamentoDTO;
import com.bradesco.pix.pix.rabbitMq.RabbitMQProducer;
import com.bradesco.pix.pix.repository.RequisicaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository repository;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    public void pagamento(RequisicaoPagamentoDTO requisicaoPagamentoDTO) {
        RequisicaoPagamento transacoes = RequisicaoPagamento
                .builder()
                .codigoPedido(requisicaoPagamentoDTO.getCodigoPedido())
                .nomeProduto(requisicaoPagamentoDTO.getNomeProduto())
                .precoProduto(requisicaoPagamentoDTO.getPrecoProduto())
                .pix(requisicaoPagamentoDTO.getPix())
                .build();

        //Salva a transação
        repository.save(transacoes);

        //Gera o QRCode
        gerarQRCode(transacoes);

        //Postar na loja que o produto foi pago
        produtoStatusPago(transacoes);

    }

    private void produtoStatusPago(RequisicaoPagamento transacoes) {
        transacoes.setStatusPagamento(StatusPagamento.PAGO);
        ObjectMapper mapper = new ObjectMapper();
        try {
           String json = mapper.writeValueAsString(transacoes);
           rabbitMQProducer.sendMessage(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void gerarQRCode(RequisicaoPagamento transacoes) {
        try {
            String data = transacoes.toString();
            String path = "C:/Users/Marce/OneDrive/Área de Trabalho/qrcode.jpg";

            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);

            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(RequisicaoPagamento requisicaoPagamento) {
        repository.save(requisicaoPagamento);
    }
}

package com.bradesco.pix.pix.controller;

import com.bradesco.pix.pix.dto.RequisicaoPagamentoDTO;
import com.bradesco.pix.pix.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RequisicaoController {
    @Autowired
    private RequisicaoService requisicaoService;

    @PostMapping
    private ResponseEntity pagamento(@RequestBody RequisicaoPagamentoDTO requisicaoPagamentoDTO) {
        requisicaoService.pagamento(requisicaoPagamentoDTO);
        //return new ResponseEntity(cliente, HttpStatus.CREATED);
        return null;
    }
}

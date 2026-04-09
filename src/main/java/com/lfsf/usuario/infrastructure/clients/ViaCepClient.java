package com.lfsf.usuario.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ViaCep", url = "${viacep.url}")
//Usar esse tipo de URL para aplicação que podem ter alteração de url, desse jeito está apontando pra uma variável dentro de apllication.properties
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json/")
    ViaCepDTO buscaDadosEndereco(@PathVariable("cep") String cep);
}


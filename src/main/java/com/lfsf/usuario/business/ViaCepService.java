package com.lfsf.usuario.business;

import com.lfsf.usuario.infrastructure.clients.ViaCepClient;
import com.lfsf.usuario.infrastructure.clients.ViaCepDTO;
import com.lfsf.usuario.infrastructure.exceptions.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep){
            return client.buscaDadosEndereco(processarCep(cep));
    }


    private String processarCep(String cep){
        String cepFormatado = cep.replace(" ", "").
                replace("-", "");


        if(!cepFormatado.matches("\\d+")
                || !Objects.equals(cepFormatado.length(), 8)){ //número de dígitos do CEP != 8
            throw new IllegalArgumentException("O CEP contém caracteres inválidos, favor verificar");
        }

        return cepFormatado;
    }
}

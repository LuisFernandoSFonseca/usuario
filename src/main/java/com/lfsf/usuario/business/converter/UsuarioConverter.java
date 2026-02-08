package com.lfsf.usuario.business.converter;

import com.lfsf.usuario.business.dto.EnderecoDTO;
import com.lfsf.usuario.business.dto.TelefoneDTO;
import com.lfsf.usuario.business.dto.UsuarioDTO;
import com.lfsf.usuario.infrastructure.entity.Endereco;
import com.lfsf.usuario.infrastructure.entity.Telefone;
import com.lfsf.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {
    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .endereco(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
                .build();
//        Usuario usuario= new Usuario();
//        usuario.setEmail(usuarioDTO.getEmail());
//        usuario.setNome(usuario.getNome());
    }

    public List<Endereco> paraListaEndereco (List<EnderecoDTO> enderecoDTOs){
        //return enderecoDTOs.stream().map(this::paraEndereco).toList(); --> Java Stream, funciona como o For mas é menor e mais rápido
        List<Endereco> enderecos = new ArrayList<>();
        for(EnderecoDTO enderecoDTO: enderecoDTOs){
            enderecos.add(paraEndereco(enderecoDTO));

        }return enderecos;
        //Laço For
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEndereco()))
                .telefones(paraListaTelefonesDTO(usuarioDTO.getTelefones()))
                .build();
//        Usuario usuario= new Usuario();
//        usuario.setEmail(usuarioDTO.getEmail());
//        usuario.setNome(usuario.getNome());
    }

    public List<EnderecoDTO> paraListaEnderecoDTO (List<Endereco> enderecoDTOs){
        //return enderecoDTOs.stream().map(this::paraEndereco).toList(); --> Java Stream, funciona como o For mas é menor e mais rápido
        List<EnderecoDTO> enderecos = new ArrayList<>();
        for(Endereco enderecoDTO: enderecoDTOs){
            enderecos.add(paraEnderecoDTO(enderecoDTO));

        }return enderecos;
        //Laço For
    }

    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome()) //ternário, como um if(?) Else (:)
                .id((entity.getId()))
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .endereco(entity.getEndereco())
                .telefones(entity.getTelefones())
                .build();
    }
}

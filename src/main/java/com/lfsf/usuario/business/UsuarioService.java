package com.lfsf.usuario.business;

import com.lfsf.usuario.business.converter.UsuarioConverter;
import com.lfsf.usuario.business.dto.UsuarioDTO;
import com.lfsf.usuario.infrastructure.entity.Usuario;
import com.lfsf.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
//        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
//        usuario = usuarioRepository.save(usuario);
//        return usuarioConverter.paraUsuarioDTO(usuario);
        // Como uma equação, podemos eliminar a segunda linha-->
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

}

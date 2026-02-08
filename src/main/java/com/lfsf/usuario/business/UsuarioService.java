package com.lfsf.usuario.business;

import com.lfsf.usuario.business.converter.UsuarioConverter;
import com.lfsf.usuario.business.dto.UsuarioDTO;
import com.lfsf.usuario.infrastructure.entity.Usuario;
import com.lfsf.usuario.infrastructure.exceptions.ConflictException;
import com.lfsf.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.lfsf.usuario.infrastructure.repository.UsuarioRepository;
import com.lfsf.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
//        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
//        usuario = usuarioRepository.save(usuario);
//        return usuarioConverter.paraUsuarioDTO(usuario);
        // Como uma equação, podemos eliminar a segunda linha-->
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        }catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario (String token, UsuarioDTO dto){
        //Buscamos o e-mail do usuário através do token (Para tirar a obrigatoriedade de passar o e-mail)
        String email = jwtUtil.extractEmailFromToken(token.substring(7));
        //Re-encriptar a senha, caso o usuário passe/altere a senha
        dto.setSenha(dto.getSenha() != null? passwordEncoder.encode(dto.getSenha()) : null);
        //Busca os dados do usuário no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não localizado"));
        //Mesclou os dados que recebemos na requisição DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        //Salvou os dados do usuário convertido e depois pegou o retorno e converteu para usuário DTO
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

}

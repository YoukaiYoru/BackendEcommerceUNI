package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.dto.UsuarioDTO;
import org.backend.trabajo.backendproyecto.model.Usuario;
import org.backend.trabajo.backendproyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> convierteDatos(List<Usuario> usuario){
        return usuario.stream()
                .map(u -> new UsuarioDTO(u.getIdUsuario(),u.getUsrLogin(),u.getUsrNombre(),u.getUsrApellido(),u.getUsrCorreo(),u.getUsrTelefono()))
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> obtenerTodosUsuarios() {
        return convierteDatos(usuarioRepository.findAll());
    }

    public List<UsuarioDTO> obtenerPorLogin(String login) {
        return convierteDatos(usuarioRepository.findByUsrLoginIs(login));
    }

}

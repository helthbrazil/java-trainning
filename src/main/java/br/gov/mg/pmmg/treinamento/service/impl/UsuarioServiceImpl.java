package br.gov.mg.pmmg.treinamento.service.impl;

import br.gov.mg.pmmg.treinamento.dto.UsuarioRequestDTO;
import br.gov.mg.pmmg.treinamento.dto.UsuarioResponseDTO;
import br.gov.mg.pmmg.treinamento.exception.EmailAlreadyExistsException;
import br.gov.mg.pmmg.treinamento.exception.UsuarioNotFoundException;
import br.gov.mg.pmmg.treinamento.model.Usuario;
import br.gov.mg.pmmg.treinamento.repository.UsuarioRepository;
import br.gov.mg.pmmg.treinamento.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO request) {
        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("O e-mail informado já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setDataCadastro(OffsetDateTime.now());

        Usuario salvo = repository.save(usuario);
        return mapToResponse(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado."));

        return mapToResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado."));

        if (!usuario.getEmail().equals(request.email()) && repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("O e-mail informado já está em uso.");
        }

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());

        Usuario atualizado = repository.save(usuario);
        return mapToResponse(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuário não encontrado.");
        }

        repository.deleteById(id);
    }

    private UsuarioResponseDTO mapToResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCadastro()
        );
    }
}

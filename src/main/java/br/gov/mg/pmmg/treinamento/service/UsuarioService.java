package br.gov.mg.pmmg.treinamento.service;

import br.gov.mg.pmmg.treinamento.dto.UsuarioRequestDTO;
import br.gov.mg.pmmg.treinamento.dto.UsuarioResponseDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO salvar(UsuarioRequestDTO request);
    UsuarioResponseDTO buscarPorId(Long id);
    List<UsuarioResponseDTO> listarTodos();
    UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO request);
    void deletar(Long id);
}

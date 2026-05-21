package br.gov.mg.pmmg.treinamento.service.impl;

import br.gov.mg.pmmg.treinamento.dto.UsuarioRequestDTO;
import br.gov.mg.pmmg.treinamento.dto.UsuarioResponseDTO;
import br.gov.mg.pmmg.treinamento.exception.EmailAlreadyExistsException;
import br.gov.mg.pmmg.treinamento.exception.UsuarioNotFoundException;
import br.gov.mg.pmmg.treinamento.model.Usuario;
import br.gov.mg.pmmg.treinamento.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioServiceImpl service;

    @Test
    void deveSalvarUsuarioComSucessoQuandoDadosForemValidos() {
        // Arrange
        UsuarioRequestDTO request = new UsuarioRequestDTO("João Silva", "joao.silva@pmmg.mg.gov.br");
        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setIdUsuario(1L);
        usuarioSalvo.setNome(request.nome());
        usuarioSalvo.setEmail(request.email());
        usuarioSalvo.setDataCadastro(OffsetDateTime.now());

        when(repository.existsByEmail(request.email())).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        // Act
        UsuarioResponseDTO response = service.salvar(request);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.idUsuario()).isEqualTo(1L);
        assertThat(response.nome()).isEqualTo(request.nome());
        assertThat(response.email()).isEqualTo(request.email());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    void deveLancarEmailAlreadyExistsExceptionQuandoEmailJaExistir() {
        // Arrange
        UsuarioRequestDTO request = new UsuarioRequestDTO("João Silva", "joao.silva@pmmg.mg.gov.br");
        when(repository.existsByEmail(request.email())).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> service.salvar(request))
                .isInstanceOf(EmailAlreadyExistsException.class)
                .hasMessageContaining("O e-mail informado já está em uso.");

        verify(repository, never()).save(any(Usuario.class));
    }

    @Test
    void deveBuscarUsuarioPorIdComSucessoQuandoIdExistir() {
        // Arrange
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        usuario.setNome("João Silva");
        usuario.setEmail("joao.silva@pmmg.mg.gov.br");
        usuario.setDataCadastro(OffsetDateTime.now());

        when(repository.findById(id)).thenReturn(Optional.of(usuario));

        // Act
        UsuarioResponseDTO response = service.buscarPorId(id);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.idUsuario()).isEqualTo(id);
        assertThat(response.nome()).isEqualTo("João Silva");
    }

    @Test
    void deveLancarUsuarioNotFoundExceptionQuandoIdNaoExistir() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(UsuarioNotFoundException.class)
                .hasMessageContaining("Usuário não encontrado.");
    }
}

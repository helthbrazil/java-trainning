package br.gov.mg.pmmg.treinamento.dto;

import java.time.OffsetDateTime;

public record UsuarioResponseDTO(
    Long idUsuario,
    String nome,
    String email,
    OffsetDateTime dataCadastro
) {}

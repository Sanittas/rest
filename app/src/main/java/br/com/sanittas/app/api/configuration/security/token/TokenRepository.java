package br.com.sanittas.app.api.configuration.security.token;

import br.com.sanittas.app.api.configuration.security.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


//    Método para recuperar todos os tokens válidos
    @Query("""
            SELECT t from Token t inner join Usuario u on t.usuario.id = u.id where u.id = :usuarioId and (t.expired = false or t.revoked = false)
            """)
    List<Token> findAllValidTokensByUsuarioId(Integer usuarioId);

    Optional<Token> findByToken(String token);
}

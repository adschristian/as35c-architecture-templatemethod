package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.PaisDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;

@Log
public class PaisDAO extends AbstractDAO<PaisDTO> {

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE pais ("
                    + "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_pais_pk PRIMARY KEY,"
                    + "nome varchar(255),"
                    + "sigla varchar(3),"
                    + "codigoTelefone int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public PaisDTO listarPorId(int id) {
        return this.listarTodos().stream().filter(e -> e.getId() == id).findAny().orElseThrow(RuntimeException::new);
    }

    @Override
    protected String stringSQLIncluir() {
        return "insert into pais(nome, sigla, codigoTelefone) values (?, ?, ?)";
    }

    @Override
    protected void setStatementIncluir(PreparedStatement preparedStm, PaisDTO pais) {
        try {
            preparedStm.setString(1, pais.getNome());
            preparedStm.setString(2, pais.getSigla());
            preparedStm.setInt(3, pais.getCodigoTelefone());
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    @Override
    protected String stringSQLListarTodos() {
        return "select * from pais";
    }

    @Override
    protected PaisDTO getEntidade(ResultSet result) {
        try {
            return PaisDTO.builder()
                    .id(result.getInt("id"))
                    .nome(result.getString("nome"))
                    .sigla(result.getString("sigla"))
                    .codigoTelefone(result.getInt("codigoTelefone"))
                    .build();
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return PaisDTO.builder().build();
    }

    @Override
    protected String stringSQLAlterar() {
        return "update pais set nome=?, sigla=?, codigoTelefone=? where id=?";
    }

    @Override
    protected void setStatementAlterar(PreparedStatement preparedStm, PaisDTO pais) {
        try {
            preparedStm.setString(1, pais.getNome());
            preparedStm.setString(2, pais.getSigla());
            preparedStm.setInt(3, pais.getCodigoTelefone());
            preparedStm.setInt(4, pais.getId());
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    @Override
    protected String stringSQLExcluir() {
        return "delete from pais where id=?";
    }

    @Override
    protected void setStatementExcluir(PreparedStatement preparedStm, int id) {
        try {
            preparedStm.setInt(1, id);
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

}

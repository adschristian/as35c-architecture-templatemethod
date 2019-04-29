package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author christian
 * @param <T>
 */
public abstract class AbstractDAO<T> {

    public boolean incluir(T entidade) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            PreparedStatement statement = conn.prepareStatement(stringSQLIncluir());
            setStatementIncluir(statement, entidade);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
                return true;

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return false;
    }

    public List<T> listarTodos() {
        List<T> resultado = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = stringSQLListarTodos();

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()) {
                resultado.add(getEntidade(result));
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return resultado;
    }
    
    public boolean alterar(T entidade) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {
            String sql = stringSQLAlterar();

            PreparedStatement statement = conn.prepareStatement(sql);
            
            setStatementAlterar(statement, entidade);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0)
                return true;
            
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return false;
    }
    
    public boolean excluir(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            String sql = stringSQLExcluir();

            PreparedStatement statement = conn.prepareStatement(sql);
            setStatementExcluir(statement, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }

        return false;
    }
    
    
    protected abstract String stringSQLIncluir();
    
    protected abstract void setStatementIncluir(PreparedStatement preparedStm, T entidade);
    
    protected abstract String stringSQLListarTodos();
    
    protected abstract T getEntidade(ResultSet result);

    protected abstract String stringSQLAlterar();

    protected abstract void setStatementAlterar(PreparedStatement preparedStm, T entidade);

    protected abstract String stringSQLExcluir();

    protected abstract void setStatementExcluir(PreparedStatement preparedStm, int id);

}

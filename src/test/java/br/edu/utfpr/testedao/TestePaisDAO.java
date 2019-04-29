package br.edu.utfpr.testedao;

import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.PaisDTO;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author christian
 */
public class TestePaisDAO {
    
    private static PaisDAO paisDao;
    
    @BeforeClass
    public static void setup() {
        paisDao = new PaisDAO();
    }
    
    @Test
    @Ignore("passed")
    public void testaIncluirPais() {
        PaisDTO pais = PaisDTO.builder()
                .nome("Brasil")
                .sigla("BRA")
                .codigoTelefone(55)
                .build();
        Assert.assertTrue(paisDao.incluir(pais));
    }
    
    @Test
    @Ignore("passed")
    public void testaListarPais() {
        PaisDTO pais = PaisDTO.builder()
                .nome("Alemanha")
                .sigla("GER")
                .codigoTelefone(65)
                .build();
        paisDao.incluir(pais);
        Assert.assertTrue(paisDao.listarTodos().size() > 0);
    }
    
    @Test
    public void testaPegarPaisPorId() {
        PaisDTO pais = PaisDTO.builder()
                .nome("França")
                .sigla("FRA")
                .codigoTelefone(65)
                .build();
        paisDao.incluir(pais);
        PaisDTO paisRecuperado = paisDao.listarTodos()
                                .stream()
                                .filter(p -> p.getSigla().equalsIgnoreCase(pais.getSigla()))
                                .collect(Collectors.toList())
                                .get(0);
        Assert.assertTrue(paisDao.listarPorId(paisRecuperado.getId()).getNome().equals(pais.getNome()));
    }
    
    @Test
    @Ignore("passed")
    public void testaAlterarPais() {
        PaisDTO pais = PaisDTO.builder()
                .nome("Chile")
                .sigla("CHL")
                .codigoTelefone(65)
                .build();
        paisDao.incluir(pais);
        PaisDTO paisRecuperado = paisDao.listarTodos()
                                .stream()
                                .filter(p -> p.getSigla().equalsIgnoreCase(pais.getSigla()))
                                .collect(Collectors.toList())
                                .get(0);
        paisRecuperado.setNome("Irlanda");
        paisRecuperado.setSigla("IRL");
        paisRecuperado.setCodigoTelefone(35);
        Assert.assertTrue(paisDao.alterar(paisRecuperado));
    }
    
    @Test
    @Ignore("passed")
    public void testaExcluirPais() {
        PaisDTO pais = PaisDTO.builder()
                .nome("Uruguai")
                .sigla("URU")
                .codigoTelefone(52)
                .build();
        paisDao.incluir(pais);
        PaisDTO paisRecuperado = paisDao.listarTodos()
                                .stream()
                                .filter(p -> p.getSigla().equalsIgnoreCase(pais.getSigla()))
                                .collect(Collectors.toList())
                                .get(0);
        Assert.assertTrue(paisDao.excluir(paisRecuperado.getId()));
    }
}

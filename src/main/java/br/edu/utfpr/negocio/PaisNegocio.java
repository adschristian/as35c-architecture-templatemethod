package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dto.PaisDTO;

public class PaisNegocio extends AbstractNegocio<PaisDTO> {

    @Override
    public void persistirEntidade(PaisDTO entidade) {
        // Instanciar PaisDAO e persistir entidade;
    }

    @Override
    public List<PaisDTO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
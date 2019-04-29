package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;

public class ClienteNegocio extends AbstractNegocio<ClienteDTO>{

    @Override
    public void persistirEntidade(ClienteDTO entidade) {
        // Instanciar ClienteDAO e persistir objeto;
    }

    @Override
    public List<ClienteDTO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
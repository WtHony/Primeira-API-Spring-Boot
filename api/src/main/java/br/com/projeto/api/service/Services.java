package br.com.projeto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Mensagem;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.Repositorys;

@Service
public class Services {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorys acao;

    // cadastro de pessoa
    public ResponseEntity<?> cadastrar(Pessoa obj) {
        if (obj.getNome().equals("")) {
            mensagem.setMesssagem("nome precisa ser preenchindo");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() <= 0) {
            mensagem.setMesssagem("idade ivalida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // selecionar todas as pessoas do banco de dados
    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // selecionar pessoa pelo codigo
    public ResponseEntity<?> selecionarByCod(int cod) {
        if (acao.countByCod(cod) == 0) {
            mensagem.setMesssagem("codigo não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findByCod(cod), HttpStatus.OK);
        }
    }

    // editar pessoa
    public ResponseEntity<?> editar(Pessoa obj) {
        if (acao.countByCod(obj.getCod()) == 0) {
            mensagem.setMesssagem("o codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if (obj.getNome().equals("")) {
            mensagem.setMesssagem("nome precisa ser preenchindo");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() <= 0) {
            mensagem.setMesssagem("idade ivalida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    // remover pessoa
    public ResponseEntity<?> remover(int cod) {
        if (acao.countByCod(cod) == 0) {
            mensagem.setMesssagem("codigo não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            Pessoa obj = acao.findByCod(cod);
            acao.delete(obj);
            mensagem.setMesssagem("pessoa removida com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}

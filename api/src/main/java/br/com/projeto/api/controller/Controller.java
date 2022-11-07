package br.com.projeto.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.Repositorys;
import br.com.projeto.api.service.Services;

@RestController
public class Controller {

    @Autowired
    private Repositorys acao;

    @Autowired
    private Services services;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa obj) {
        return services.cadastrar(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar() {
        return services.selecionar();
    }

    @GetMapping("/api/{cod}")
    public ResponseEntity<?> selecionarPessoa(@PathVariable int cod) {
        return services.selecionarByCod(cod);
    }

    @PutMapping("/api")
    public ResponseEntity<?> editarPessoa(@RequestBody Pessoa obj) {
        return services.editar(obj);
    }

    @DeleteMapping("/api/{cod}")
    public ResponseEntity<?> deletarPessoa(@PathVariable int cod) {
        return services.remover(cod);
    }

    @GetMapping("/api/cont")
    public long contador() {
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarByNome() {
        return acao.findByOrderByNomeAsc();
    }

    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarByNome2() {
        return acao.findByNomeOrderByIdadeDesc("felipe");
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem() {
        return acao.findByNomeContaining("a");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom() {
        return acao.findByNomeStartsWith("h");
    }

    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom() {
        return acao.findByNomeEndsWith("a");
    }

    @GetMapping("/api/somaIdades")
    public int somaIdade() {
        return acao.somaIdades();
    }

    @GetMapping("/")
    public String msg() {
        return "hello, mundo!";
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "seja bem vindo(a)";
    }

    @GetMapping("/boasvindas/{nome}")
    public String boasVindas(@PathVariable String nome) {
        return "seja bem vindo(a) " + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj) {

    }
}
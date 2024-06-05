package com.trabalho.frameworkspring.control;

import com.trabalho.frameworkspring.model.Produto;
import com.trabalho.frameworkspring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProdutoController {
    @Autowired
    ProdutoRepository rep;

    /*
     * GET /api/produtos : listar todos os produtos
     */
    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getAllProdutos(@RequestParam(required = false) String codigo) {
        try {
            List<Produto> la = new ArrayList<>();

            if(codigo == null) {
                la.addAll(rep.findAll());
            } else {
                la.addAll(rep.findAllByCodigo(codigo));
            }

            if (la.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(la, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * POST /api/produtos : criar produto
     */
    @PostMapping("/produtos")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        try {
            Produto _p = rep.save(new Produto(
                    produto.getCodigo(),
                    produto.getDescricao(),
                    produto.getValor_custo(),
                    produto.getValor_venda()
            ));

            return new ResponseEntity<>(_p, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GET /api/produtos/:id : listar produto dado um id
     */
    @GetMapping("/produtos/{id_produto}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id_produto") long id_produto) {
        Optional<Produto> data = rep.findById(id_produto);

        return data.map(produto -> new ResponseEntity<>(produto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*
     * PUT /api/produtos/:id : atualizar produto dado um id
     */
    @PutMapping("/produtos/{id_produto}")
    public ResponseEntity<Produto> updateProduto(@PathVariable("id_produto") long id_produto, @RequestBody Produto produto) {
        Optional<Produto> data = rep.findById(id_produto);

        if (data.isPresent())
        {
            Produto pr = data.get();
            pr.setCodigo(produto.getCodigo());
            pr.setDescricao(produto.getDescricao());
            pr.setValor_custo(produto.getValor_custo());
            pr.setValor_venda(produto.getValor_venda());

            return new ResponseEntity<>(rep.save(pr), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /*
     * DEL /api/produtos/:id_produto : remover produto dado um id
     */
    @DeleteMapping("/produtos/{id_produto}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("id_produto") long id_produto)
    {
        try {
            rep.deleteById(id_produto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * DEL /api/produtos : remover todos os produtos
     */
    @DeleteMapping("/produtos")
    public ResponseEntity<HttpStatus> deleteAllProduto()
    {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

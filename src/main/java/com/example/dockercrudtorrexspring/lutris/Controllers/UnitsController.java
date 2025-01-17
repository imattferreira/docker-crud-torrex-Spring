package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Unit;
import com.example.dockercrudtorrexspring.lutris.Services.UnitsServices;
import org.springframework.http.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/units")
public class UnitsController {

    UnitsServices unitsServices;

    public UnitsController() throws NoSuchAlgorithmException {
        this.unitsServices = new UnitsServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Unit>> getAll(@RequestBody Unit unit) throws SQLException {
        ArrayList<Unit> units = this.unitsServices.getAll();

        return new ResponseEntity<>(units, HttpStatus.OK);
    }


    @GetMapping(path ="/{id}", produces = "application/json")
    public ResponseEntity<Unit> findOne(@PathVariable("id") int id) throws SQLException {
        var result = this.unitsServices.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
        // pegar "id" como parametro na url
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Unit> create(@RequestBody Unit unit) throws SQLException {
        System.out.println(unit);
        System.out.println(unit.getName());
        var result = this.unitsServices.create(unit);

        return new ResponseEntity<>(result, HttpStatus.CREATED);

        // pegar todos os campos do body da resuisicao (JSON)
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Unit> update(@PathVariable("id") int id, @RequestBody Unit unit) throws SQLException {
        var result = this.unitsServices.findOne(id);

        if(result != null) {
            result.setName(result.getName());
            result.setLaunchDate(result.getLaunchDate());

            unitsServices.update(result);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();


        // pegar "ID" como parametro na url
        // pegar os campos do body da requisicao (JSON)
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Unit> delete(@PathVariable("id") int id) throws SQLException {
        var result = this.unitsServices.findOne(id);

        if(result != null) {
            this.unitsServices.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
        // pegar "id" como parametro na URL
    }
}

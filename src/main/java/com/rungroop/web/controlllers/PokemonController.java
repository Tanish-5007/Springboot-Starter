package com.rungroop.web.controlllers;

import com.rungroop.web.dto.PokemonDto;
import com.rungroop.web.model.Pokemon;
import com.rungroop.web.service.impl.PokemonServiceImpl;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {

    @Autowired
    PokemonServiceImpl pokemonService;

    @GetMapping("/get")
    public ResponseEntity<List<PokemonDto>> getPokemon(){
        return new ResponseEntity<>(pokemonService.getPokemon(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable("id") int id){
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("/pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }





}

package com.rungroop.web.controlllers;

import com.rungroop.web.dto.PokemonDto;
import com.rungroop.web.dto.PokemonResponse;
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
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    PokemonServiceImpl pokemonService;

    @GetMapping("/get")
    public ResponseEntity<PokemonResponse> getPokemon(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
            ){

        return new ResponseEntity<>(pokemonService.getPokemon(pageNo, pageSize), HttpStatus.OK);
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

    @PutMapping("/get/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemonById(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int id){
        return ResponseEntity.ok(pokemonService.updatePokemon(pokemonDto, id));
    }

    @DeleteMapping("/get/{id}/delete")
    public ResponseEntity<String> deletePokemonById(@PathVariable("id") int id){
        pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon Deleted", HttpStatus.OK);
    }


}

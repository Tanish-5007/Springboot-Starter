package com.rungroop.web.service;

import com.rungroop.web.dto.PokemonDto;
import com.rungroop.web.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonResponse getPokemon(int pageNo, int pageSize);

    PokemonDto getPokemonById(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    void deletePokemon(int id);

}

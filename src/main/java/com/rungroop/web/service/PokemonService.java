package com.rungroop.web.service;

import com.rungroop.web.dto.PokemonDto;

import java.util.List;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    List<PokemonDto> getPokemon();

    PokemonDto getPokemonById(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    void deletePokemon(int id);

}

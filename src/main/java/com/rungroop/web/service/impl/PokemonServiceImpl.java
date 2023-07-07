package com.rungroop.web.service.impl;


import com.rungroop.web.dto.PokemonDto;
import com.rungroop.web.exceptions.PokemonNotFoundException;
import com.rungroop.web.model.Pokemon;
import com.rungroop.web.repository.PokemonRepository;
import com.rungroop.web.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService{

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<PokemonDto> getPokemon() {
//        List<Pokemon> pokemon = pokemonRepository.findAll();
//        List<PokemonDto> list = pokemon.stream().map(pokemon1 -> mapToDto(pokemon1)).collect(Collectors.toList());
//        return list;
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        List<PokemonDto> pokemonDtoList = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            PokemonDto pokemonDto = mapToDto(pokemon);
            pokemonDtoList.add(pokemonDto);
        }
        return pokemonDtoList;
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon newPokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));

        return mapToDto(newPokemon);
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse;
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon newPokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));

        newPokemon.setName(pokemonDto.getName());
        newPokemon.setType(pokemonDto.getType());

        Pokemon updatedPokemon = pokemonRepository.save(newPokemon);

        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemon(int id) {

        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be Deleted"));

        pokemonRepository.deleteById(id);
    }


    private PokemonDto mapToDto(Pokemon pokemon){
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto){
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }

}

package com.rungroop.web.service.impl;


import com.rungroop.web.dto.PokemonDto;
import com.rungroop.web.dto.PokemonResponse;
import com.rungroop.web.exceptions.PokemonNotFoundException;
import com.rungroop.web.model.Pokemon;
import com.rungroop.web.repository.PokemonRepository;
import com.rungroop.web.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public PokemonResponse getPokemon(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemonList = pokemonRepository.findAll(pageable);

        List<Pokemon> listOfPokemon = pokemonList.getContent();

        List<PokemonDto> pokemonDtoList = new ArrayList<>();
        for (Pokemon pokemon: listOfPokemon) {
            PokemonDto pokemonDto = mapToDto(pokemon);
            pokemonDtoList.add(pokemonDto);
        }
//        List<PokemonDto> pokemonDtoList = pokemon.stream().map(pokemon1 -> mapToDto(pokemon1)).collect(Collectors.toList());
//        return pokemonDtoList;
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(pokemonDtoList);
        pokemonResponse.setPageNo(pokemonList.getNumber());
        pokemonResponse.setPageSize(pokemonList.getSize());
        pokemonResponse.setTotalElement(pokemonList.getTotalElements());
        pokemonResponse.setTotalPages(pokemonList.getTotalPages());
        pokemonResponse.setLast(pokemonList.isLast());

        return pokemonResponse;
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

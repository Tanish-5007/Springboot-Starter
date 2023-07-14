package com.rungroop.web.service.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rungroop.web.dto.PokemonDto;
import com.rungroop.web.dto.PokemonResponse;
import com.rungroop.web.model.Pokemon;
import com.rungroop.web.repository.PokemonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PokemonServiceImplTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPokemon() {
        // Mocking the repository's findAll method
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "Pikachu", "Electric"));
        pokemons.add(new Pokemon(2, "Charizard", "Fire/Flying"));

        Pageable pageable = PageRequest.of(0, 10);
        Page<Pokemon> pokemonPage = new PageImpl<>(pokemons, pageable, pokemons.size());
        when(pokemonRepository.findAll(pageable)).thenReturn(pokemonPage);

        // Calling the actual method
        PokemonResponse response = pokemonService.getPokemon(0, 10);

        // Assertions
        assertEquals(2, response.getContent().size());
        assertEquals(0, response.getPageNo());
        assertEquals(10, response.getPageSize());
        assertEquals(2, response.getTotalElement());
        assertEquals(1, response.getTotalPages());
        assertEquals(true, response.isLast());

        verify(pokemonRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetPokemonById() {
        // Mocking the repository's findById method
        int pokemonId = 1;
        Pokemon pokemon = new Pokemon(pokemonId, "Pikachu", "Electric");
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));

        // Calling the actual method
        PokemonDto pokemonDto = pokemonService.getPokemonById(pokemonId);

        // Assertions
        assertEquals(pokemon.getId(), pokemonDto.getId());
        assertEquals(pokemon.getName(), pokemonDto.getName());
        assertEquals(pokemon.getType(), pokemonDto.getType());

        verify(pokemonRepository, times(1)).findById(pokemonId);
    }

    @Test
    public void testCreatePokemon() {
        // Mocking the repository's save method
        PokemonDto pokemonDto = new PokemonDto(1, "Pikachu", "Electric");
        Pokemon pokemon = new Pokemon(1, "Pikachu", "Electric");
        when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemon);

        // Calling the actual method
        PokemonDto createdPokemon = pokemonService.createPokemon(pokemonDto);

        // Assertions
        assertEquals(pokemon.getId(), createdPokemon.getId());
        assertEquals(pokemon.getName(), createdPokemon.getName());
        assertEquals(pokemon.getType(), createdPokemon.getType());

        verify(pokemonRepository, times(1)).save(any(Pokemon.class));
    }

    @Test
    public void testUpdatePokemon() {
        // Mocking the repository's findById and save methods
        int pokemonId = 1;
        PokemonDto updatedPokemonDto = new PokemonDto(pokemonId, "Pikachu", "Electric");
        Pokemon existingPokemon = new Pokemon(pokemonId, "Pikachu", "Electric");
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(existingPokemon));
        when(pokemonRepository.save(any(Pokemon.class))).thenReturn(existingPokemon);

        // Calling the actual method
        PokemonDto updatedPokemon = pokemonService.updatePokemon(updatedPokemonDto, pokemonId);

        // Assertions
        assertEquals(existingPokemon.getId(), updatedPokemon.getId());
        assertEquals(updatedPokemonDto.getName(), updatedPokemon.getName());
        assertEquals(updatedPokemonDto.getType(), updatedPokemon.getType());

        verify(pokemonRepository, times(1)).findById(pokemonId);
        verify(pokemonRepository, times(1)).save(any(Pokemon.class));
    }

    @Test
    public void testDeletePokemon() {
        // Mocking the repository's findById and deleteById methods
        int pokemonId = 1;
        Pokemon pokemon = new Pokemon(pokemonId, "Pikachu", "Electric");
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));

        // Calling the actual method
        pokemonService.deletePokemon(pokemonId);

        // Verifying that the repository's deleteById method is called
        verify(pokemonRepository, times(1)).deleteById(pokemonId);
    }
}
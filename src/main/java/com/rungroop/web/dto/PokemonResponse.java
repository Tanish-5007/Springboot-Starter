package com.rungroop.web.dto;


import com.rungroop.web.model.Pokemon;
import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private List<PokemonDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}

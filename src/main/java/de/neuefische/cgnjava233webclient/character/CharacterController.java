package de.neuefische.cgnjava233webclient.character;

import de.neuefische.cgnjava233webclient.rickandmortyapi.RickAndMortyCharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

	private final RickAndMortyCharacterService rickAndMortyCharacterService;

	@GetMapping
	public List<Character> getAllCharacters(@RequestParam(required = false) String page){
		if(page != null){
			return rickAndMortyCharacterService.fetchAllCharacters(Integer.parseInt(page));
		}
		return rickAndMortyCharacterService.fetchAllCharacters();
	}

}

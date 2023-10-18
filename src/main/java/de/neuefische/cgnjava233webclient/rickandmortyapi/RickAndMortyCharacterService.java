package de.neuefische.cgnjava233webclient.rickandmortyapi;

import de.neuefische.cgnjava233webclient.character.Character;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class RickAndMortyCharacterService {
	private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

	private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

	public List<Character> fetchAllCharacters(int page) {
		if (page < 1) {
			throw new IllegalArgumentException("Page must be greater than zero.");
		}
		RickAndMortyCharacterApiResponse response = Objects.requireNonNull(webClient
						.get()
						.uri(uriBuilder -> uriBuilder
								.queryParam("page", page)
								.build())
						.retrieve()
						.toEntity(RickAndMortyCharacterApiResponse.class)
						.block())
				.getBody();
		assert response != null;
		return response.results();
	}

	public List<Character> fetchAllCharacters() {
		RickAndMortyCharacterApiResponse response = Objects.requireNonNull(webClient
						.get()
						.retrieve()
						.toEntity(RickAndMortyCharacterApiResponse.class)
						.block())
				.getBody();
		assert response != null;
		return response.results();
	}

}

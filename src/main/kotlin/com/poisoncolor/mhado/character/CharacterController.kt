package com.poisoncolor.mhado.character

import com.poisoncolor.mhado.archetype.Archetype
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/v1/character")
class CharacterController(val characterService: CharacterService) {

    @GetMapping
    fun getCharacters(): List<Character> {
        return characterService.getCharacters()
    }

    @GetMapping("{characterId}")
    fun getCharacter(@PathVariable("characterId") characterId: Long): Character {
        return characterService.getCharacter(characterId)
    }

    @PostMapping
    fun registerNewCharacter(@RequestBody character: Character) {
        characterService.addNewCharacter(character)
    }

    @PutMapping("{characterId}")
    fun updateCharacter(
            @PathVariable("characterId") characterId: Long,
            @RequestParam firstname: Optional<String>,
            @RequestParam lastname: Optional<String>,
            @RequestParam archetype: Optional<Archetype>,
    ) {
        characterService.updateCharacter(characterId, firstname, lastname, archetype)
    }

    @PutMapping("{characterId}/stat")
    fun updateCharacterStats(
            @PathVariable("characterId") characterId: Long,
            @RequestBody stats: Map<String, Int>
    ) {
        characterService.updateCharacterStats(characterId, stats)
    }

    @PutMapping("{characterId}/experience")
    fun addCharacterExperience(
            @PathVariable("characterId") characterId: Long,
            @RequestParam experience: Int
    ) {
        characterService.addCharacterExperience(characterId, experience)
    }

    @DeleteMapping("{characterId}")
    fun deleteCharacter(@PathVariable("characterId") id: Long) {
        characterService.deleteCharacter(id)
    }
}
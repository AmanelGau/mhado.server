package com.poisoncolor.mhado.character

import com.poisoncolor.mhado.archetype.Archetype
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Objects
import java.util.Optional
import java.util.function.Supplier
import kotlin.math.pow

@Service
class CharacterService(private final val characterRepository: CharacterRepository) {


    fun getCharacters(): List<Character> {
        return characterRepository.findAll()
    }

    fun getCharacter(characterId: Long): Character {
        return characterRepository.findById(characterId).orElseThrow(Supplier { IllegalStateException("character with id $characterId does not exists") })
    }

    fun addNewCharacter(character: Character) {
        val charactersByName =  characterRepository.findCharacterByFullname(character.firstname + " " + character.lastname)
        if (charactersByName.isPresent) {
            throw java.lang.IllegalStateException("name taken")
        }
        characterRepository.save(character)
    }

    fun deleteCharacter(characterId: Long) {
        val exists = characterRepository.existsById(characterId)
        if (!exists) {
            throw java.lang.IllegalStateException("character with id $characterId does not exists")
        }
        characterRepository.deleteById(characterId)
    }

    @Transactional
    fun updateCharacter(characterId: Long, firstname: Optional<String>, lastname: Optional<String>, archetype: Optional<Archetype>) {
        val character: Character = characterRepository.findById(characterId).orElseThrow(Supplier { IllegalStateException("character with id $characterId does not exists") })

        if (firstname.isPresent && !firstname.isEmpty && !Objects.equals(character.firstname,firstname.get())) {
            val characterOptional: Optional<Character> = characterRepository.findCharacterByFullname("$firstname ${character.lastname}" )
            if (characterOptional.isPresent) {
                throw IllegalStateException("name taken")
            }
            character.firstname = firstname.get()
        }

        if (lastname.isPresent && !lastname.isEmpty && !Objects.equals(character.lastname,lastname.get())) {
            val characterOptional: Optional<Character> = characterRepository.findCharacterByFullname("${character.firstname} $lastname")
            if (characterOptional.isPresent) {
                throw IllegalStateException("name taken")
            }
            character.firstname = lastname.get()
        }


        if (archetype.isPresent && !archetype.isEmpty && !Objects.equals(character.archetype,archetype.get())) {
            character.archetype = archetype.get()
        }
    }

    @Transactional
    fun updateCharacterStats(characterId: Long, stats: Map<String, Int>) {
        val character: Character = characterRepository.findById(characterId).orElseThrow(Supplier { IllegalStateException("character with id $characterId does not exists") })

        if ( Objects.equals(character.stats, stats)) {
            stats.values.forEach { stat -> if(stat < 0) throw IllegalStateException("stats can't be below 0")}
        }

        if (character.statPoint < stats.values.sum() - character.stats.values.sum()) {
            throw java.lang.IllegalStateException("not enough stat point")
        }
        character.statPoint -= stats.values.sum() - character.stats.values.sum()
        character.stats = stats
    }

    @Transactional
    fun addCharacterExperience(characterId: Long, experience: Int) {
        val character: Character = characterRepository.findById(characterId).orElseThrow(Supplier { IllegalStateException("character with id $characterId does not exists") })

        if (experience < 0) {
            throw java.lang.IllegalStateException("added experience can't be less than 0")
        }

        character.experience += experience

        while ( character.experience >= character.maxExperience) {
            val oldFloor = character.level/5
            character.experience -= character.maxExperience
            character.level++
            character.statPoint += character.level
            if (character.level/5 > oldFloor ){
                character.maxExperience *= 2
            }
        }
    }

}
package com.poisoncolor.mhado.equipment

import com.poisoncolor.mhado.character.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface EquipmentRepository : JpaRepository<Equipment, Long> {

    @Query("SELECT e FROM Equipment e WHERE ownerId = ?1")
    fun findByCharacter(characterId: Long): Optional<List<Equipment>>;

    @Query("SELECT e FROM Equipment e WHERE name = ?1 AND owner = ?2")
    fun findEquipmentsByNameAndOwner(name: String,  character: Character): Optional<Equipment>;
}
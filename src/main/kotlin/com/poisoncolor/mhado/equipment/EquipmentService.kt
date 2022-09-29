package com.poisoncolor.mhado.equipment;

import com.poisoncolor.mhado.character.CharacterRepository
import org.springframework.stereotype.Service;
import java.util.function.Supplier

@Service
class EquipmentService(private final val equipmentRepository: EquipmentRepository, private final val characterRepository: CharacterRepository) {

    fun getAllEquipments(): List<Equipment> {
        return equipmentRepository.findAll();
    }

    fun getEquipments(characterId: Long): List<Equipment> {
        return equipmentRepository.findByCharacter(characterId).orElseThrow(Supplier { IllegalStateException("character with id $characterId does not exists") })
    }

    fun addNewEquipment(equipment: Equipment) {
        val equipmentsByNameAndOwner =  equipmentRepository.findEquipmentsByNameAndOwner(equipment.name, equipment.owner)
        if (equipmentsByNameAndOwner.isPresent) {
            throw java.lang.IllegalStateException("equipment already exists")
        }
        equipmentRepository.save(equipment)
    }
}

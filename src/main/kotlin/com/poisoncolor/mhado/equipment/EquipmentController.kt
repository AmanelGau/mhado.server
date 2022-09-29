package com.poisoncolor.mhado.equipment

import com.poisoncolor.mhado.character.Character
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/v1/equipment")
class EquipmentController(val equipmentService: EquipmentService) {

    @GetMapping()
    fun getAllEquipments(): List<Equipment> {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("{characterId}")
    fun getEquipments(@PathVariable("characterId") characterId: Long): List<Equipment> {
        return equipmentService.getEquipments(characterId)
    }

    @PostMapping
    fun registerNewEquipment(@RequestBody equipment: Equipment) {
        equipmentService.addNewEquipment(equipment)
    }

}
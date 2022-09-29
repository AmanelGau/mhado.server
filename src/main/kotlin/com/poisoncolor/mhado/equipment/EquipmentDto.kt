package com.poisoncolor.mhado.equipment

data class EquipmentDto(
        var type: EquipementType,
        var name: String,
        var stats: Map<String, Int>,
        var description: String?,
        var ownerId: Long
);

/* Utiliser les DTO (comme celui-ci) au lieu des Entities dans les Controllers
 * Dans la méthode Create des Services, passer le DTO en input, mais c'est là où tu le transformes en Entity
 * Dans les méthodes Get des Services, faire le mapping inverse
 */
package com.poisoncolor.mhado.equipment

import com.poisoncolor.mhado.stat.Stat
import com.poisoncolor.mhado.character.Character
import org.springframework.lang.Nullable
import javax.persistence.*

@Entity
@Table
data class Equipment(
    @Id
    @SequenceGenerator(
            name = "character_sequence",
            sequenceName = "character_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "character_sequence"
    )
    val id: Long = 0,
    var type: EquipementType,
    var name: String,
    @ElementCollection
    @CollectionTable(name = "stat_equipment_mapping")
    @MapKeyColumn(name = "stat_name")
    @Column(name = "value")
    var stats: Map<String, Int>,
    @Nullable
    var description: String?,
    @ManyToOne(fetch = FetchType.EAGER)
    var owner: Character
)

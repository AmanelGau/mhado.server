package com.poisoncolor.mhado.character

import com.poisoncolor.mhado.stat.Stat
import com.poisoncolor.mhado.archetype.Archetype;
import javax.persistence.*

@Entity
@Table
data class Character(
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
    var firstname: String,
    var lastname: String,
    var archetype: Archetype,
    var level: Int = 1,
    var experience: Int = 0,
    var maxExperience: Int = 100,
    var statPoint: Int = 10,
    @ElementCollection
    @CollectionTable(name = "stat_mapping")
    @MapKeyColumn(name = "stat_name")
    @Column(name = "value")
    var stats: Map<String, Int> = mapOf(
        Stat.STRENGTH.stat to 0,
        Stat.RESISTANCE.stat to 0,
        Stat.AGILITY.stat to 0,
        Stat.LUCK.stat to 0,
        Stat.EXPLORATION.stat to 0,
        Stat.SPIRIT.stat to 0
    )
)
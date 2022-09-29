package com.poisoncolor.mhado.stat

import javax.persistence.Enumerated

enum class Stat(val stat: String) {
    STRENGTH("strength"),
    RESISTANCE("resistance"),
    AGILITY("agility"),
    LUCK("luck"),
    EXPLORATION("exploration"),
    SPIRIT("spirit")
}
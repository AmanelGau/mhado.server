package com.poisoncolor.mhado.character

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CharacterRepository : JpaRepository<Character, Long> {

    @Query("SELECT c FROM Character c WHERE CONCAT(c.firstname, ' ', c.lastname) = ?1")
    fun findCharacterByFullname(fullname: String): Optional<Character>;

}
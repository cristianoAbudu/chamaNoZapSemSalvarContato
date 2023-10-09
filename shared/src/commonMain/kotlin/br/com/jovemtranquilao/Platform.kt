package br.com.jovemtranquilao

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
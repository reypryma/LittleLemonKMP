package org.re.kmplittlelemon

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
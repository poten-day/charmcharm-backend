package com.charm.charm.domain.color

enum class Color(
    val hex: String,
    val display: String,
) {
    RED("#FF6D6D", "빨간색"),
    ORANGE("#FF996D", "주황색"),
    YELLOW("#FFC700", "노란색"),
    GREEN("#7DC55B", "초록색"),
    BLUE("#5B89FF", "파란색"),
    SKY_BLUE("#70C3FF", "하늘색"),
    PURPLE("#A073FF", "보라색"),
    PINK("#FF7394", "핑크색"),
    WHITE("#9CB5CD", "흰색"),
    BLACK("#000000", "검은색"),
    ;

    companion object {
        fun from(display: String) = values().firstOrNull { display.contains(it.display) }
            ?: throw RuntimeException("존재하지 않는 색상입니다.")

        fun contains(display: String) = values().any { display.contains(it.display) }
    }
}

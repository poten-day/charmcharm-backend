package com.charm.charm.domain.form

data class QuestionOption(
    val id: Long,
    val description: String,
) {
    fun toDbString() = "$id$QUESTION_OPTION_DEliMITER$description"

    companion object {
        private const val QUESTION_OPTION_DEliMITER = "|"
        fun from(value: String): QuestionOption {
            val (id, description) = value.split(QUESTION_OPTION_DEliMITER)
            return QuestionOption(id.toLong(), description)
        }
    }
}

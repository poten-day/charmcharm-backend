package com.charm.charm.domain.form

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class AnswerConverter : AttributeConverter<List<QuestionOption>, String> {
    override fun convertToDatabaseColumn(attribute: List<QuestionOption>): String {
        return attribute.joinToString(ANSWER_DELIMITER) { it.toDbString() }
    }

    override fun convertToEntityAttribute(dbData: String): List<QuestionOption> {
        return dbData.split(ANSWER_DELIMITER)
            .map { QuestionOption.from(it) }
    }

    companion object {
        private const val ANSWER_DELIMITER = ","
    }
}

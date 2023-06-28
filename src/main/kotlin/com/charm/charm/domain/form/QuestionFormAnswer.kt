package com.charm.charm.domain.form

import com.charm.charm.domain.charmer.Charmer
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class QuestionFormAnswer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,

    @JoinColumn(name = "question_form_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var questionForm: QuestionForm,

    @JoinColumn(name = "charmer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var charmer: Charmer,

    @Convert(converter = AnswerConverter::class)
    @Column(name = "selected_question_options_ids")
    var questionOptions: MutableList<QuestionOption> = mutableListOf(),
)

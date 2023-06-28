CREATE TABLE charmer
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(15)  NOT NULL,
    uuid      VARCHAR(255) NOT NULL,
    open_time TIMESTAMP    NOT NULL
);

CREATE TABLE question_form
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_title   VARCHAR(255) NOT NULL,
    is_single_choice BOOLEAN      NOT NULL,
    min_answer_count INT          NOT NULL DEFAULT 1,
    question_options TEXT         NOT NULL,
    CONSTRAINT chk_min_answer_count CHECK (min_answer_count >= 0)
);

CREATE TABLE question_form_answer
(
    id                            BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_form_id              BIGINT NOT NULL,
    charmer_id                    BIGINT NOT NULL,
    selected_question_options_ids TEXT   NOT NULL
);

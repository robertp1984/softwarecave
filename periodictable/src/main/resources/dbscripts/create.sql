CREATE TABLE pt_element_category(
    id NUMERIC,
    name VARCHAR(256) NOT NULL,
    description VARCHAR(1024) NOT NULL,
    CONSTRAINT pt_elem_cat_id_pk PRIMARY KEY(id),
    CONSTRAINT pt_elem_cat_name_unique UNIQUE(name)
);

CREATE SEQUENCE pt_element_category_seq;

CREATE TABLE pt_element(
    id NUMERIC,
    atomic_number NUMERIC NOT NULL,
    name VARCHAR(256) NOT NULL,
    symbol VARCHAR(256) NOT NULL,
    category_id NUMERIC NOT NULL,
    CONSTRAINT pt_elem_id_pk PRIMARY KEY(id),
    CONSTRAINT pt_elem_atomic_number_unique UNIQUE(atomic_number),
    CONSTRAINT pt_elem_name_unique UNIQUE(name),
    CONSTRAINT pt_elem_category_id_fk FOREIGN KEY (category_id) REFERENCES pt_element_category(id)
);

CREATE SEQUENCE pt_element_seq;


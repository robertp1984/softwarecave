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

INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'OTHER_NON_METAL', 'Other nonmetal');
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 1, 'Hydrogen', 'H', pt_element_category_seq.currval);
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 6, 'Carbon', 'C', pt_element_category_seq.currval);
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 7, 'Nitrogen', 'N', pt_element_category_seq.currval);
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 8, 'Oxygen', 'O', pt_element_category_seq.currval);

INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'NOBLE_GAS', 'Noble gas');
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 2, 'Helium', 'He', pt_element_category_seq.currval);
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 10, 'Neon', 'Ne', pt_element_category_seq.currval);

INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'METALLOID', 'Metalloid');
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 5, 'Boron', 'B', pt_element_category_seq.currval);

INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'HALOGEN', 'Halogen');
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 9, 'Fluorine', 'F', pt_element_category_seq.currval);


INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'ALKALI_METAL', 'Alkali metal');
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 3, 'Lithium', 'Li', pt_element_category_seq.currval);

INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'ALKALINE_EARTH_METAL', 'Alkaline earth metal');
INSERT INTO pt_element (id, atomic_number, name, symbol, category_id) values(pt_element_seq.nextval, 4, 'Beryllium', 'Be', pt_element_category_seq.currval);

INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'LANTHANOID', 'Lanthanoid');
INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'ACTINOID', 'Actinoid');
INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'TRANSITION_METAL', 'Transition metal');
INSERT INTO pt_element_category(id, name, description) values(pt_element_category_seq.nextval, 'POST_TRANSITION_METAL', 'Post transition metal');


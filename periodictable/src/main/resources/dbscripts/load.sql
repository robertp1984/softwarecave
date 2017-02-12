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


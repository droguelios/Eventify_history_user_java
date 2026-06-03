
INSERT INTO categories (name, description) VALUES ('Conciertos', 'Eventos musicales en vivo');
INSERT INTO categories (name, description) VALUES ('Teatro', 'Obras y representaciones teatrales');
INSERT INTO categories (name, description) VALUES ('Deportes', 'Eventos deportivos y competencias');

INSERT INTO venues (places, city, capacity) VALUES ('Estadio El Campín', 'Bogotá', 45000);
INSERT INTO venues (places, city, capacity) VALUES ('Movistar Arena', 'Bogotá', 14000);
INSERT INTO venues (places, city, capacity) VALUES ('Estadio Atanasio Girardot', 'Medellín', 44000);

INSERT INTO events (name, active, date, description, venue_id) VALUES ('Concierto de Rock Colombiano', TRUE, '2026-07-15', 'Gran concierto de rock con bandas nacionales', 2);
INSERT INTO events (name, active, date, description, venue_id) VALUES ('Final de Fútbol Local', TRUE, '2026-06-20', 'Partido decisivo del campeonato local', 3);
INSERT INTO events (name, active, date, description, venue_id) VALUES ('Obra de Teatro Agotada', FALSE, '2026-08-01', 'Obra completamente vendida', 2);
INSERT INTO events (name, active, date, description, venue_id) VALUES ('Festival de Jazz Nocturno', TRUE, '2026-09-10', 'Noche de jazz con artistas internacionales', 1);

INSERT INTO event_categories (event_id, category_id) VALUES (1, 1);
INSERT INTO event_categories (event_id, category_id) VALUES (2, 3);
INSERT INTO event_categories (event_id, category_id) VALUES (3, 2);
INSERT INTO event_categories (event_id, category_id) VALUES (4, 1);

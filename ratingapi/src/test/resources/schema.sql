DROP TABLE IF EXISTS RATING;
CREATE TABLE RATING (
                        id IDENTITY NOT NULL PRIMARY KEY,
                        movie_title VARCHAR(500) NOT NULL,
                        rating_score NUMERIC(20, 2),
                        no_of_rating INT
);

insert into RATING values (1, 'Black Swan',5.0,1);
insert into RATING values (2, 'The Fighter',6.0,1);
insert into RATING values (3, 'Inception',5.5,1);
insert into RATING values (4, 'Avatar',4.5,1);
insert into RATING values (5, 'Milk',5.8,1);
insert into RATING values (6, 'The Reader',5.75,1);
insert into RATING values (7, 'Juno',5.9,1);
insert into RATING values (8, 'The Departed',6.0,1);
insert into RATING values (9, 'Crash',5.7,1);
insert into RATING values (10, 'Bay',9.0,1);
insert into RATING values (11, 'The Hours',9.7,1);
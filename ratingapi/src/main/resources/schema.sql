DROP TABLE IF EXISTS RATING;
CREATE TABLE RATING (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    movie_title VARCHAR(500) NOT NULL,
    rating_score NUMERIC(20, 2),
    no_of_rating INT
);
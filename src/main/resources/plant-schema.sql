DROP TABLE IF EXISTS plants CASCADE;

CREATE TABLE  plants (
id INTEGER AUTO_INCREMENT, 
flower_colour VARCHAR(255), 
foliage_colour VARCHAR(255), 
name VARCHAR(255), 
planting_month VARCHAR(255), 
planting_position VARCHAR(255), 
PRIMARY KEY (id));
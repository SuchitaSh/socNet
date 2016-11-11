DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS friends;

CREATE  TABLE users (
	
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL UNIQUE ,
  password VARCHAR(100) NOT NULL ,
  first_name VARCHAR(100) NOT NULL ,
  last_name VARCHAR(100) NOT NULL, 
  email VARCHAR(100),
  date_of_birth DATE, 
  PRIMARY KEY (id));
  
CREATE TABLE posts(

	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(100),

	text TEXT NOT NULL,
	posting_date DATE NOT NULL,	
	user_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES users(id)
);
  
  CREATE TABLE comments(

	id INT NOT NULL AUTO_INCREMENT,
	comment_text TEXT NOT NULL,
	post_id INT NOT NULL,
	user_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(post_id) REFERENCES posts(id),
	FOREIGN KEY(user_id) REFERENCES users(id)
);

  CREATE TABLE friends(

	first_user_id INT NOT NULL,
	second_user_id INT NOT NULL,
	FOREIGN KEY(first_user_id) REFERENCES users(id),
	FOREIGN KEY(second_user_id) REFERENCES users(id)
  );

  
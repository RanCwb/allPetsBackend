-- Criar tabela institutions
CREATE TABLE institutions (
    id_institution INT AUTO_INCREMENT PRIMARY KEY,
    cnpj VARCHAR(20) NOT NULL,
    institution_name VARCHAR(255) NOT NULL,
    responsible_name VARCHAR(255),
    responsible_phone VARCHAR(20),
    email VARCHAR(255),
    address TEXT,
    is_paying BOOLEAN DEFAULT FALSE
);

-- Criar tabela admin_users
CREATE TABLE admin_users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    id_institution INT NOT NULL,
    CONSTRAINT fk_admin_users_institutions FOREIGN KEY (id_institution)
        REFERENCES institutions(id_institution) ON DELETE CASCADE
);

-- Criar tabela customers
CREATE TABLE customers (
    id_customer INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    password VARCHAR(255) NOT NULL
);

-- Criar tabela animals
CREATE TABLE animals (
    id_animal INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT,
    species VARCHAR(100),
    gender VARCHAR(50),
    weight DECIMAL(10,2),
    available_for_adoption BOOLEAN DEFAULT TRUE,
    parentage INT,
    description TEXT,
    id_institution INT NOT NULL,
    CONSTRAINT fk_animals_institutions FOREIGN KEY (id_institution)
        REFERENCES institutions(id_institution) ON DELETE CASCADE,
    CONSTRAINT fk_animals_parentage FOREIGN KEY (parentage)
        REFERENCES animals(id_animal) ON DELETE SET NULL
);

-- Criar tabela vaccines
CREATE TABLE vaccines (
    id_vaccine INT AUTO_INCREMENT PRIMARY KEY,
    id_animal INT NOT NULL,
    vaccine_name VARCHAR(255) NOT NULL,
    vaccine_date DATE,
    observations TEXT,
    CONSTRAINT fk_vaccines_animals FOREIGN KEY (id_animal)
        REFERENCES animals(id_animal) ON DELETE CASCADE
);

-- Criar tabela messages
CREATE TABLE messages (
    id_message INT AUTO_INCREMENT PRIMARY KEY,
    id_sender INT NOT NULL,
    id_receiver INT NOT NULL,
    message_content TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_messages_sender FOREIGN KEY (id_sender)
        REFERENCES admin_users(id_user) ON DELETE CASCADE,
    CONSTRAINT fk_messages_receiver FOREIGN KEY (id_receiver)
        REFERENCES customers(id_customer) ON DELETE CASCADE
);

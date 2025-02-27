DROP TABLE IF EXISTS product_details;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS tipo_armazones;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS formas;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS marcas;

CREATE TABLE marcas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    foto_url VARCHAR(255) NOT NULL,
    foto_public_id VARCHAR(255) NOT NULL
);

CREATE TABLE formas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE tipo_armazones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    precio DECIMAL(10, 2) NOT NULL,
    descripcion TEXT NOT NULL,
    tipo_armazon_id INT,
    marca_id INT,
    categoria_id INT,
    forma_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (tipo_armazon_id) REFERENCES tipo_armazones(id),
    FOREIGN KEY (marca_id) REFERENCES marcas(id),
    FOREIGN KEY (categoria_id) REFERENCES categories(id),
    FOREIGN KEY (forma_id) REFERENCES formas(id)
);

CREATE TABLE product_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT,
    color VARCHAR(255) NOT NULL,
    talla VARCHAR(255) NOT NULL,
    longitud_varilla VARCHAR(255) NOT NULL,
    ancho_puente VARCHAR(255) NOT NULL,
    ancho_total VARCHAR(255) NOT NULL,
    image_id INT,
    sku VARCHAR(255) NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES products(id),
    FOREIGN KEY (image_id) REFERENCES images(id)
);

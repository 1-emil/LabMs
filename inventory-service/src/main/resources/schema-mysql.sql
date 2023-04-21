USE `inventory-db`;


create table if not exists inventories (
                                           id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                           inventory_id VARCHAR(36),
    type VARCHAR(100) UNIQUE
    );

create table if not exists electronic_options (
                                                  electronic_id INTEGER,
                                                  name VARCHAR(100),
    description VARCHAR(200),
    cost DECIMAL(19,2)
    );

create table if not exists electronics (
                                           id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                           ein VARCHAR(50),
    inventory_id VARCHAR(36),
    status VARCHAR(50),
    usage_type VARCHAR(50),
    manufacturer VARCHAR(100),
    model VARCHAR(75),
    msrp DECIMAL(19,2),
    cost DECIMAL(19,2),
    total_options_cost DECIMAL(19,2)
    );
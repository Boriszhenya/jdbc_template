ALTER TABLE world.city
    MODIFY COLUMN District VARCHAR(255) NULL;

UPDATE world.city SET District = NULL WHERE (ID = 6);

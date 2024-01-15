CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCityWithMaxPopulation`()
BEGIN
    SELECT *
    FROM world.city
    WHERE Population = (SELECT MAX(Population) FROM world.city);
END
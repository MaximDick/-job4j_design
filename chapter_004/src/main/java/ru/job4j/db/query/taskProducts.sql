--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product AS p INNER JOIN type AS t ON p.id = t.id  where t.name = 'сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product as p INNER JOIN type as t on p.name = t.name WHERE p.name like ('%мороженное%');

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
--С
 --этим запросом затруднение. Как сделать по дате?

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product WHERE price = (SELECT MAX(price) FROM product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.


--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product as p INNER JOIN typeA as t on p.id = t.id WHERE t.name = 'сыр' or t.name = 'молоко';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT * FROM product AS p INNER JOIN type AS t ON p.id = t.id where p.type_id < 10;
--8. Вывести все продукты и их тип.
SELECT * FROM product AS p INNER JOIN type AS t ON p.id = t.id;
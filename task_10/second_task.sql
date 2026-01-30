-- 1
select model,speed,hd from PC
where price < 500::money;

-- 2
select maker from product
where type = 'Printer';

-- 3
select model, ram, screen from laptop
where price >1000::money;

-- 4
select * from printer
where color = 'y';

-- 5
select model, speed, hd from PC
where (cd = '12x' or cd = '24x') and price < 600::money ;

-- 6
select product.maker, laptop.speed from laptop
join product on laptop.model = product.model
where hd > 100;

-- 7
select laptop.model, price from product
join laptop on laptop.model = product.model
where maker = 'B'

union all

select PC.model, price from product
join PC on PC.model = product.model
where maker = 'B'

union all

select printer.model, price from product
join printer on printer.model = product.model
where maker = 'B';

-- 8
select distinct maker from product
where type = 'PC' and maker not in (
	select distinct maker from product
	where type = 'Laptop'
);

-- 9
select distinct product.maker from product
join PC on PC.model = product.model
where PC.speed >= 450;

-- 10
select model, price from printer
where price = (select MAX(price) from printer);

-- 11
select AVG(speed) from PC;

-- 12
select AVG(speed) from laptop
where price > 1000::money;

-- 13
select avg(PC.speed) from PC
join product on product.model = PC.model
where product.maker = 'A';

-- 14
select speed, avg(price::numeric) from PC
group by speed;

-- 15
select hd from PC
group by hd
having count(*) >= 2;

-- 16
select DISTINCT p1.model as model_higher, p2.model as model_lower, p1.speed, p1.ram
from PC as p1
join PC as p2 on p1.speed = p2.speed
	and p1.ram = p2.ram
	and p1.model > p2.model;

-- 17
select product.type ,laptop.model, laptop.speed from laptop
join product ON product.model = laptop.model
where laptop.speed < all (select pc.speed from pc);

-- 18
select product.maker, printer.price from product
join printer ON printer.model = product.model
where printer.color = 'y' and printer.price = (select min(price) from printer);

-- 19
select product.maker, avg(laptop.screen) from product
join laptop ON laptop.model = product.model
group by product.maker;

-- 20
select product.maker, count(distinct product.model) from product
join pc ON pc.model = product.model
group by product.maker
having count(distinct  product.model) >=3;

-- 21
select product.maker, max(pc.price::numeric) from product
join pc ON pc.model = product.model
group by product.maker;

-- 22
select speed, avg(price::numeric) from pc
where speed > 600
group by speed;

-- 23
select distinct laptop_maker.maker from ( 
	select distinct product.maker from product join laptop on laptop.model = product.model
	where laptop.speed >= 750
) as laptop_maker
join (
	select distinct product.maker from product join pc on pc.model = product.model
	where pc.speed >= 750
) as pc_maker on laptop_maker.maker = pc_maker.maker;

-- 24
select model from (
	select pc.price as price, product.model as model from product 
	join pc ON pc.model = product.model
	union all
	select laptop.price as price, product.model as model from product 
	join laptop ON laptop.model = product.model
	union all
	select printer.price as price, product.model as model  from product 
	join printer ON printer.model = product.model
)
where price::numeric = (select max(max_price::numeric) from (
	select max(price::numeric) as max_price from PC
	union all
	select max(price::numeric) as max_price from laptop
	union all
	select max(price::numeric) as max_price from printer
)
);

-- 25

select distinct maker
from product where type = 'Printer'
and maker in(
	select product.maker from product
	join pc on pc.model = product.model
	where pc.ram = (select min(ram) from pc)
	and pc.speed = (select max(speed) from pc
		where pc.ram = (select min(ram) from pc)
	)
);
	


alter table physicians add active tinyint;
update physicians set active = 1;
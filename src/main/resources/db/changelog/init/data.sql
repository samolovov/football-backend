-- players
insert into players values (1, 'Павел', 'Самоловов', '1988-02-24', 'MIDFIELDER', '', '');
insert into players values (2, 'Евгений', 'Ветров', '1990-06-11', 'DEFENDER', '', '');
insert into players values (3, 'Игорь', 'Митин', '1989-10-12', 'FORWARD', '', '');
insert into players values (4, 'Владимир', 'Рубо', '1985-03-13', 'GOALKEEPER', '', '');
insert into players values (5, 'Антон', 'Шабуров', '1987-07-24', 'FORWARD', '', '');
insert into players values (6, 'Игорь', 'Арефьев', '1988-12-07', 'DEFENDER', '', '');
insert into players values (7, 'Семён', 'Толстов', '1993-01-21', 'MIDFIELDER', '', '');
insert into players values (8, 'Евгений', 'Бондаренко', '1991-08-19', 'GOALKEEPER', '', '');
insert into players values (9, 'Максим', 'Лаврухин', '1988-09-15', 'GOALKEEPER', '', '');
insert into players values (10, 'Евгений', 'Горкунов', '1985-11-04', 'MIDFIELDER', '', '');
insert into players values (11, 'Анатолий', 'Белоног', '1988-02-24', 'DEFENDER', '', '');
insert into players values (12, 'Андрей', 'Соболев', '1983-01-01', 'FORWARD', '', '');
-- teams
insert into teams values (1, 'NSU Stars', '2019-06-06', 'Сборная звёзд НГУ прошлых лет', '');
insert into teams values (2, 'Домино', '2010-01-01', 'Легендарное Домино', '');
insert into teams values (3, 'Спектр 7-6', '2010-01-01', 'Солянка сборная мясная', '');
-- referees
insert into referees values (1, 'Константин', 'Раченко', '1989-12-12','');
insert into referees values (2, 'Сергей', 'Овчинников', '1989-12-12','');
-- tournaments
insert into tournaments values (1, 'Зимнее первенство СОРАН');
-- seasons
insert into seasons values (1, 1, '2020-11-10', '2021-04-01');
-- season_teams
insert into season_teams values (1, 1, 1);
insert into season_teams values (2, 1, 2);
insert into season_teams values (3, 1, 3);
-- season_team_players
insert into season_team_players values (1, 1, 1);
insert into season_team_players values (2, 1, 2);
insert into season_team_players values (3, 1, 3);
insert into season_team_players values (4, 1, 4);
insert into season_team_players values (5, 2, 5);
insert into season_team_players values (6, 2, 6);
insert into season_team_players values (7, 2, 7);
insert into season_team_players values (8, 2, 8);
insert into season_team_players values (9, 3, 9);
insert into season_team_players values (10, 3, 10);
insert into season_team_players values (11, 3, 11);
insert into season_team_players values (12, 3, 12);
-- games
insert into games values (1, 1, 1, 2, 7, 3, '2019-03-12', 1);
insert into games values (2, 1, 2, 3, 1, 2, '2019-03-13', 2);
insert into games values (3, 1, 1, 3, 4, 1, '2019-03-14', 1);
-- game details
insert into game_details values (1, 1, 1, 1, 'NORMAL_GOAL', 10);
insert into game_details values (2, 1, 1, 1, 'NORMAL_GOAL', 11);
insert into game_details values (3, 1, 1, 1, 'NORMAL_GOAL', 12);
insert into game_details values (4, 1, 1, 2, 'PENALTY_GOAL', 13);
insert into game_details values (5, 1, 1, 2, 'NORMAL_GOAL', 14);
insert into game_details values (6, 1, 1, 3, 'NORMAL_GOAL', 15);
insert into game_details values (7, 1, 1, 3, 'NORMAL_GOAL', 16);
insert into game_details values (8, 1, 2, 5, 'NORMAL_GOAL', 17);
insert into game_details values (9, 1, 2, 6, 'NORMAL_GOAL', 17);
insert into game_details values (10, 1, 2, 7, 'NORMAL_GOAL', 17);
insert into game_details values (11, 1, 2, 7, 'RED', 18);

insert into game_details values (12, 2, 2, 5, 'NORMAL_GOAL', 10);
insert into game_details values (13, 2, 3, 10, 'NORMAL_GOAL', 11);
insert into game_details values (14, 2, 3, 11, 'NORMAL_GOAL', 12);
insert into game_details values (15, 2, 3, 12, 'YELLOW', 13);

insert into game_details values (16, 3, 1, 1, 'PENALTY_GOAL', 10);
insert into game_details values (17, 3, 1, 1, 'NORMAL_GOAL', 11);
insert into game_details values (18, 3, 1, 2, 'NORMAL_GOAL', 12);
insert into game_details values (19, 3, 3, 10, 'AUTO_GOAL', 13);
insert into game_details values (20, 3, 3, 11, 'NORMAL_GOAL', 14);
insert into game_details values (21, 3, 3, 12, 'RED', 14);
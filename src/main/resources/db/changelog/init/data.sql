insert into referees values (1, 'Konstantin', 'Rachenko', '1989-12-12','');
insert into teams values (1, 'NSU Stars', '2019-06-06', 'Сборная звёзд НГУ прошлых лет', '');
insert into teams values (2, 'Domino', '2010-01-01', 'Легендарное Домино', '');
insert into players values (1, 'Pavel', 'Samolovov', '1988-02-24', 'MIDFIELDER', 'Родился в городе Саяногорск, Хакасия', '');
insert into players values (2, 'Igor', 'Arefiev', '1999-01-01', 'DEFENDER', 'Очень маленький игрок', '');
insert into tournaments values (1, 'Зимнее первенство СОРАН');
insert into seasons values (1, 1, '2019-11-10', '2020-04-01');
insert into season_teams values (1, 1, 1);
insert into season_teams values (2, 1, 2);
insert into season_team_players values (1, 1, 1);
insert into season_team_players values (2, 2, 2);
insert into games values (1, 1, 1, 2, 3, 0, 1);
-- game details
insert into game_details values (1, 1, 1, 1, 'GOAL', 20);
insert into game_details values (2, 1, 1, 1, 'GOAL', 25);
insert into game_details values (3, 1, 1, 1, 'GOAL', 50);
insert into game_details values (4, 1, 2, 2, 'RED', 45);


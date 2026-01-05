-- 뒤에 주소 변경 예정

-- 월드맵
INSERT INTO world_map (id, name, world_url) VALUES
(1, '헤네시스', 'https://maple.gg/world/victoria'),
(2, '엘나스', 'https://maple.gg/world/el-nath'),
(3, '루디브리엄', 'https://maple.gg/world/ludibrium');

-- 메이플맵
INSERT INTO maple_map (id, name, map_url, world_map_id) VALUES
(1, '헤네시스 사냥터 1', 'https://maple.gg/map/henesys', 1),
(2, '엘리니아', 'https://maple.gg/map/ellinia', 1),
(3, '페리온', 'https://maple.gg/map/perion', 1),

(4, '엘나스 산맥', 'https://maple.gg/map/el-nath-mountain', 2),
(5, '얼음골짜기', 'https://maple.gg/map/ice-valley', 2),

(6, '장난감 공장', 'https://maple.gg/map/toy-factory', 3),
(7, '시간의 길', 'https://maple.gg/map/path-of-time', 3);

-- 레이드 몹
INSERT INTO raid_mob (id, name, image_url) VALUES
(1, '자쿰', 'https://maple.gg/mob/zakum.png'),
(2, '혼테일', 'https://maple.gg/mob/horntail.png'),
(3, '핑크빈', 'https://maple.gg/mob/pinkbean.png'),
(4, '루시드', 'https://maple.gg/mob/lucid.png');
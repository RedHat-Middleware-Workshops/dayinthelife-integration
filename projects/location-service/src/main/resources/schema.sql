DROP TABLE IF EXISTS locations; 

CREATE TABLE locations (
  id integer NOT NULL,
  name varchar(255) NOT NULL,
  lat varchar(30) NOT NULL,
  lng varchar(30) NOT NULL,
  location_type varchar(255) NOT NULL,
  status varchar(1) NOT NULL
);

INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (1,'Johannesburg','-25.879392','28.029105','Local Office','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (2,'Boston','42.34651','-71.05148','Local Office','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (3,'Riyadh','26.602074','46.890817','Local Office','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (4,'Barcelona','41.394274','2.145745','Local Office','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (5,'Raleigh','35.77518','-78.63763','Headquarter','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (6,'Mexico City','19.426913','-99.171802','Local Office','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (7,'Sunnyvale','37.37797','-122.03336','Local Office','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (8,'London','51.509815','0.085312','Headquarter','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (9,'Sydney','-33.82272','151.208372','Headquarter','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (10,'New Delhi','28.551827','77.122683','Local Office','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (11,'Rio de Janeiro','-22.950996','-43.1766953','Headquarter','1');
INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (12,'Tokyo','35.645852','139.711016','Local Office','1');

CREATE TABLE location_detail (
  id integer NOT NULL,
  phone varchar(30) NOT NULL,
  owner varchar(100) NOT NULL,
  operating_hour varchar(200) NOT NULL
);

INSERT INTO location_detail (id,phone,owner,operating_hour) VALUES (1,'345-678-9567','XXX XXX', '8:00 AM - 5:00 PM');
INSERT INTO location_detail (id,phone,owner,operating_hour) VALUES (2,'546-232-7467','XXX XXX', '9:00 AM - 6:00 PM');
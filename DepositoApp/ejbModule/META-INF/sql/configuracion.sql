--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso) VALUES (1,'DES-G01','192.168.43.253:8080','jms/queue/portal01', NULL,'async');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso) VALUES (1,'DES-G01','localhost:8080','jms/queues/LocalServer1Q', NULL,'async');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso) VALUES (2,'MON-G01','localhost:8080','CreateWS/LogLM', NULL,'sync');

INSERT INTO tipo_articulos (1,'Limpieza');
INSERT INTO tipo_articulos (1,'Cocina');
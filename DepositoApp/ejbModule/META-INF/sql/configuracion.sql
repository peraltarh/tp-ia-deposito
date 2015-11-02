--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso) VALUES (1,'DES-G01','192.168.43.253:8080','jms/queue/portal01', NULL,'async');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (1,'DES-G01','localhost:8080','jms/queues/LocalServer1Q', NULL,'async','s');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (2,'MON-G01','localhost:8080','CreateWS/LogLM', NULL,'sync','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (3,'MON-G01','localhost:8080','MonitoreoWEB/WSInformeAuditoriaBean', NULL,'sync','n');

INSERT INTO tipo_articulos VALUES (1,'Limpieza');
INSERT INTO tipo_articulos VALUES (2,'Cocina');
-- Categorias
INSERT INTO tipo_articulos VALUES (1,'Electrodomésticos');
INSERT INTO tipo_articulos VALUES (2,'Moda');
INSERT INTO tipo_articulos VALUES (3,'Muebles');
INSERT INTO tipo_articulos VALUES (4,'Niños');
--

-- Envio articulos Despacho,Portal
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (1,'POR-G02','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (2,'POR-G06','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (3,'POR-G10','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (4,'POR-G14','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');

INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (6,'DES-G07','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (7,'DES-G08','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (8,'DES-G11','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (9,'DES-G15','IP_A_DEFINIR:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
--

-- Envio log de creacion de Articulo a Monitor
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (11,'MON-G01','IP_A_DEFINIR:8080','MonitoreoWEB/WSInformeAuditoriaBean', NULL,'sync','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (12,'MON-G03','172.16.161.7:8080','MonitoreoEJB/WSInformeAuditoriaBean', NULL,'sync','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (13,'MON-G13','IP_A_DEFINIR:8080','MonitoreoWEB/WSInformeAuditoriaBean', NULL,'sync','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (14,'MON-G16','IP_A_DEFINIR:8080','MonitoreoWEB/WSInformeAuditoriaBean', NULL,'sync','n');

INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (15,'MON-G01','IP_A_DEFINIR:8080','jms/queue/MonitorQueue', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (16,'MON-G03','172.16.161.7:8080','jms/queue/MonitorQueue', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (17,'MON-G13','IP_A_DEFINIR:8080','jms/queue/MonitorQueue', NULL,'async','n');
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (18,'MON-G16','IP_A_DEFINIR:8080','jms/queue/MonitorQueue', NULL,'async','n');
--

-- Test
INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (99999,'MON-G01','localhost:8080','jms/queues/LocalServer1Q', NULL,'async','n');
--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (1,'DES-G01','172.16.165.28:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
-- INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso) VALUES (1,'DES-G01','192.168.43.76:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async');
--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (2,'MON-G01','localhost:8080','CreateWS/LogLM', NULL,'sync','n');
--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (3,'MON-G01','localhost:8080','MonitoreoWEB/WSInformeAuditoriaBean', NULL,'sync','n');
--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (1,'DES-G01','localhost:8080','jms/queues/LocalServer1Q', NULL,'async','s');


--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (888888,'MON-G03','172.20.10.5:8080','MonitoreoEJB/WSInformeAuditoriaBean', NULL,'sync','n');
--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (777777,'DES-G08','172.16.165.28:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (666666,'DES-G08','172.16.165.33:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');
--INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (555555,'DES-G08','172.16.165.31:8080','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');

INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (333333,'MON-G08','3454dbaa.ngrok.io','MonitoreoEJB/WSInformeAuditoriaBean', NULL,'sync','n');

INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (555555,'POR-G02','dcdf676a.ngrok.io','jms/queue/RecepcionArticulosDeposito', NULL,'async','n');

INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (444444,'DES-G08','dd20c08c.ngrok.io','TPO-DespachoG7-WEB/rest/solicitudes/recibirArticulos', NULL,'sync','n');

INSERT INTO configuracion (IdConfiguracion,IdGrupo,url,recurso ,descripcion,tipoRecurso,activo) VALUES (222222,'DES-G02','74938c8f.ngrok.io','DespachoWeb/rest/solicitudes/recibirArticulos', NULL,'sync','n');

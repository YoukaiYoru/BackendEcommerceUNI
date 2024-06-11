INSERT INTO dbo.orden_estado(id_orden_estado, orden_estado_nombre)
VALUES 
    (1, 'pedido'),
    (2, 'pagado'),
    (3, 'recibido');

SELECT * FROM dbo.orden_estado
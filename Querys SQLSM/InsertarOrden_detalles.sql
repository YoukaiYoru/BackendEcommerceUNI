INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (1, 3, 61.00, 2, 3)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (2, 2, 97.00, 12, 5)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (3, 1, 40.00, 3, 9)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (4, 2, 30.00, 2, 15)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (5, 4, 30.00, 7, 20)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (6, 5, 35.00, 5, 23)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (7, 1, 58.00, 6, 30)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (8, 1, 40.00, 9, 52)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (9, 2, 96.00, 8, 60)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (10, 3, 59.00, 10, 69)

INSERT INTO dbo.orden_detalles (id_orden_detalles, cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (11, 4, 45.00, 11, 77)


SELECT * FROM dbo.orden_detalles

SELECT producto.*, orden_detalles.*, orden.id_orden AS Expr1, orden_estado.*
FROM     producto INNER JOIN
                  orden_detalles ON producto.id_producto = orden_detalles.id_producto INNER JOIN
                  orden ON orden_detalles.id_orden = orden.id_orden INNER JOIN
                  orden_estado ON orden.id_orden_estado = orden_estado.id_orden_estado
WHERE  (orden.id_orden = 2)

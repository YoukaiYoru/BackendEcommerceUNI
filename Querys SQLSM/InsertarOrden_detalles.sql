INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio,  id_orden, id_producto)
VALUES ( 3, 61.00, 2, 3)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (2, 97.00, 12, 5)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (1, 40.00, 3, 9)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (2, 30.00, 2, 15)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES ( 4, 30.00, 7, 20)

INSERT INTO dbo.orden_detalles (producto_precio, id_orden, id_producto)
VALUES (5, 35.00, 5, 23)

INSERT INTO dbo.orden_detalles (producto_precio, id_orden, id_producto)
VALUES ( 1, 58.00, 6, 30)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (1, 40.00, 9, 52)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (2, 96.00, 8, 60)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (3, 59.00, 10, 69)

INSERT INTO dbo.orden_detalles (cantidad_producto, producto_precio, id_orden, id_producto)
VALUES (4, 45.00, 11, 77)

DECLARE @ProductoPrecio FLOAT;
DECLARE @CantidadProducto INT;
DECLARE @OrdenID INT;
DECLARE @ProductoID INT;

-- Asigna valores a las variables
SET @ProductoPrecio = 10.5; -- Precio del producto
SET @CantidadProducto = 3;  -- Cantidad de productos
SET @OrdenID = 22;           -- ID de la orden
SET @ProductoID = 2;        -- ID del producto

-- Calcula el subtotal
DECLARE @SubTotal FLOAT;
SET @SubTotal = @ProductoPrecio * @CantidadProducto;

-- Inserta datos en la tabla OrdenDetalles
INSERT INTO dbo.orden_detalles(producto_precio,cantidad_producto,sub_total_precio,id_orden,id_producto)
VALUES (@ProductoPrecio, @CantidadProducto, @SubTotal, @OrdenID, @ProductoID);


SELECT * FROM dbo.orden_detalles

SELECT producto.*, orden_detalles.*, orden.id_orden AS Expr1, orden_estado.*
FROM     producto INNER JOIN
                  orden_detalles ON producto.id_producto = orden_detalles.id_producto INNER JOIN
                  orden ON orden_detalles.id_orden = orden.id_orden INNER JOIN
                  orden_estado ON orden.id_orden_estado = orden_estado.id_orden_estado
WHERE  (orden.id_orden = 2)

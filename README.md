# ms-pedidos

## Descripción
Microservicio REST desarrollado con Spring Boot para gestionar los pedidos realizados por clientes. Permite registrar pedidos, listarlos, buscar por ID, actualizar su estado y cancelarlos.

## Tecnologías utilizadas
- Java 21
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- PostgreSQL (Neon)
- Lombok
- Docker

## Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/pedidos | Crear pedido |
| GET | /api/pedidos | Listar todos los pedidos |
| GET | /api/pedidos/{id} | Buscar pedido por ID |
| PATCH | /api/pedidos/{id}/estado | Actualizar estado del pedido |
| DELETE | /api/pedidos/{id} | Cancelar pedido |

### Estados válidos
`REGISTRADO` | `PAGADO` | `ENVIADO` | `CANCELADO`

## Variables de entorno necesarias

| Variable | Descripción |
|----------|-------------|
| DB_URL | URL de conexión a PostgreSQL en Neon |
| DB_USERNAME | Usuario de la base de datos |
| DB_PASSWORD | Contraseña de la base de datos |
| PORT | Puerto del servidor (default: 8081) |

## Instrucciones para ejecutar en local

1. Clonar el repositorio:
```bash
git clone https://github.com/Arturo1603/ms-pedidos.git
cd ms-pedidos
```

2. Configurar las variables de entorno en tu IDE o terminal:
```
DB_URL=jdbc:postgresql://tu-host.neon.tech/neondb?sslmode=require
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_password
```

3. Ejecutar con Maven:
```bash
mvn spring-boot:run
```

4. Probar en:
```
http://localhost:8081/api/pedidos
```

## Instrucciones de despliegue en Render

1. Crear un nuevo **Web Service** en [Render](https://render.com)
2. Conectar el repositorio de GitHub
3. Render detecta el `Dockerfile` automáticamente
4. Agregar las variables de entorno: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
5. Click en **Deploy Web Service**

## URL del servicio desplegado

```
https://ms-pedidos-0vd2.onrender.com/api/pedidos
```

## Ejemplos de uso de la API

### Crear pedido (POST)
```bash
POST /api/pedidos
Content-Type: application/json

{
  "cliente": "Juan Pérez",
  "correoCliente": "juan@email.com",
  "productoId": 1,
  "nombreProducto": "Laptop Lenovo",
  "cantidad": 2,
  "precioUnitario": 3500.00
}
```
> El campo `total` se calcula automáticamente en el backend: `cantidad × precioUnitario`

### Listar todos los pedidos (GET)
```bash
GET /api/pedidos
```

### Buscar pedido por ID (GET)
```bash
GET /api/pedidos/1
```

### Actualizar estado del pedido (PATCH)
```bash
PATCH /api/pedidos/1/estado
Content-Type: application/json

{
  "estado": "PAGADO"
}
```

### Eliminar pedido (DELETE)
```bash
DELETE /api/pedidos/1
```
> Eliminación lógica: cambia el estado a `CANCELADO`

## Ejemplo de respuesta exitosa

```json
{
  "id": 1,
  "cliente": "Juan Pérez",
  "correoCliente": "juan@email.com",
  "productoId": 1,
  "nombreProducto": "Laptop Lenovo",
  "cantidad": 2,
  "precioUnitario": 3500.00,
  "total": 7000.00,
  "estado": "REGISTRADO",
  "fechaPedido": "2026-05-17T10:00:00"
}
```

## Ejemplo de respuesta de error

```json
{
  "mensaje": "Pedido no encontrado",
  "detalle": "No existe un pedido con el ID 10",
  "fecha": "2026-05-17T10:00:00"
}
```

OBSERVACION: Debido a que el servicio utilizado (RENDER) esta configurado en su version gratuita, aunque este activo, por falta de uso entra en un estado de “dormido”, por es las rutas pueden demorar en cargar 60 segundos, y luego al actualizar cargar con normalidad y responder a las peticiones.

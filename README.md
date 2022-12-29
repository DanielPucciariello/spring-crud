TP CODO A CODO,  MODULO SPRING.

Descripción del proyecto:

El trabajo consiste en hacer un CRUD sobre una base de datos con tablas relacionadas. 
En el caso particular de este trabajo la base de datos consiste de dos tablas relacionadas de un “modo uno a varios”.
Una contiene un listado de restaurantes (lado “uno” de la relación). Los restaurantes tienen como datos: el nombre, la dirección, el teléfono y una lista de platos.
La otra tabla tiene la lista de platos (lado “varios” de la relación) cada plato tiene nombre, precio, calorías y un restaurant asignado.
El CRUD se puede efectuar sobre cualquiera de las dos tablas. Se pueden crear, modificar, leer y borrar elementos de las mismas.

La personalización del trabajo consiste en el agregado de dos endpoints. Uno de ellos entrega un listado de platos del restaurant solicitado ordenado por nombre.
El otro endpoint devuelve el/los plato/s con menos calorías del listado de platos, junto a la información del restaurant al que pertenece cada uno encaso que haya mas de un elemento a listar.

A continuación, un listado de los endpoints con sus descripciones y, llegado el caso, comentarios míos sobre los mismos:
-	/crearRestaurant: Recibe por body un restaurant y lo ingresa al listado.
-	/listarRestaurant:  Lista los restaurants de la BBDD con sus platos.
-	/borraRestaurant/{id}: Dado un id por URL del restaurant que se requiere eliminar, se lo borra de la BBDD
-	/obtenerRestaurantPorId/{id}: Dado un id por URL del restaurant que se requiere conocer, se lo recupera de la BBDD
-	/actualizaRestaurant/{id}: Dado un id por URL del restaurant que se necesita modificar, se reemplazan sus datos por los suministrados por body. Vale aclarar que no se modifican los datos de los platos del restaurant en este endpoint ya que hay otro a tal efecto
-	/agregaPlato/{id}: Se agrega el plato indicado por body al restaurant id declarado en la URL
-	/eliminarPlato/{id}}:  Se indica por URL el id del plato a eliminar. Si el plato existe se elimina, en caso contrario se avisa por mensajeDto
-	/modificaPlato/{id}: Se modifica el plato indicado en el id con la información del plato indicado por body
-	/platoConMenosCalorías/: Devuelve un listado con los platos con menos calorías del listado de platos junto con la informacion del restaurant al que pertenecen
-	/listaDePlatosPorRestaurantOrdenada/{id}: Dado el id del restaurant requerido se retorna un listado con sus platos ordenados alfabéticamente por su nombre.
     
Comentario sobre los Tests. Los test implementados funcionan con los datos que se cargan al ejecutar la API.
     En caso de modificar la BBDD los mismos pueden fallar porque los datos modificados pueden hacer que los resultados de los tests no coincidan.
     Entiendo que para que esto no suceda se debe trabajar sobre una BBDD H2, pero no llegué a tiempo para implementarla y preferí avanzar lo mas posible con los test y demás ajustes al trabajo.
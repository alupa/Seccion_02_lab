# Seccion_02_lab
Laboratorio 2 Android - FruitWorld App

Objetivo
--------
Crear una app, llamada FruitWorld y con el icono visible. Tendremos un ListView por defecto al abrir la app, que contendrá frutas.
Fruta, será un modelo POJO que nosotros crearemos, con 3 atributos, nombre, origen e icono (es un int, se le pasará una referencia
con R.id a un icono que previamente tenemos que crear con Android Studio).
Crear 3 directorios dentro de nuestro paquete, con los siguientes nombres: activities, adapters, models. Alojar en cada uno, el
contenido apropiado.

Tendremos 3 opciones en el menú de opciones, Añadir fruta (siempre mismo icono, mismo origen, y mismo nombre cambiando el
número al final, con un contador), Grid View button y List View button, aunque el usuario sólo verá 2 opciones, Añadir estará
siempre, List button cuando el grid view esté renderizado, y Grid button cuando el List View esté renderizado.
Añadimos la función click sobre frutas en ambos views, aunque crearemos sólo un método y será aplicado para ambos. Aplica la
interfaz “AdapterView.OnItemClickListener”. Enseñamos un mensaje para las frutas pre cargadas, pero para las nuevas añadidas,
otro mensaje.

Crea un método para hacer el switch entre List y Grid view. La lógica del método debe seguir lo siguiente: Se le pasa como
parámetro un valor que indica a que view queremos cambiar, entonces comprobaremos si ese view no está visible, para proseguir.
Imaginemos que queremos cambiar a Grid, si grid no está visible, procedemos a esconder el List View, esconder el botón del grid en
el option menu, enseñar el Grid View y el botón para cambiar a List en el option menu.
Por último, añadiremos un context menu para borrar elementos, debe ser borrado tanto en el Grid View como en el List View, ya que
compartirán la misma lista de frutas. Este context menu, debe tener como título el nombre de la fruta pulsada.

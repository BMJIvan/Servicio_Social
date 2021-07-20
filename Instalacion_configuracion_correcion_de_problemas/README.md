<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<h1>Instalación y configuración </h1>
	<h2>Android Studio</h2>
		<p>jdk: <u>https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html</u></p>
		<p>Seleccionar de acuerdo al sistema operativo</p>
		<p>*Buscar en google: jdk java</p>
		<p>*Desde Android Studio: sdk</p>
	<h2>configuración Android Studio</h2>
		<p>Pantalla de inicio->configure->SDK Manager->SDK platforms</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen001.jpg?raw=true" width="30%" weight="auto"></p>
		<p>Instalar SDK de versión Android que se va utilizar para desarrollar y elegir carpeta donde se guardará el SDK. La ruta que se elija no debe contener acentos, ya que generará problemas al compilar la aplicación.</p>
		<p>*Se recomienda instalar una versión más alta y una versión más baja</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen002.jpg?raw=true" width="75%" weight="auto"></p>
		<p>Pantalla de inicio->configure->SDK Manager->SDK tools</p>
		<p>Se usó la siguiente configuración:</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen003.jpg?raw=true" width="75%" weight="auto"></p>
	<h2>libGDX</h2>
		<p>libGDX: <u>https://libgdx.badlogicgames.com/download.html</u></p>
		<p>*buscar en google gdx setup</p>
		<table style="width:100%">
			<tr>
				<td>
					<img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen004.jpg?raw=true" width="70%" weight="auto">
				</td>
				<td>
					<p>float="left">Name: Nombre de la aplicación.</p>
					<p>Package: .com.NombreCompania.NombreAplicación.</p>
					<p>Game Class: Nombre de la clase principal.</p>
					<p>Destination: Ruta donde se guardará la carpeta del proyecto.</p>
					<p>Android SDK: ruta del SDK.</p>
					<p></p>
					<p>Sub Projects</p>
					<p>Desktop: Para poder visualizar antes de hacer el APK.</p>
					<p>Android: Sistema operativo donde se realizaran pruebas.</p>
					<p></p>
					<p>Extensiones</p>
					<p>Free2type: Motor de físicas.</p>
					<p></p>
					<p>Generate: Generar el proyecto. Listo para usarse en diferentes plataformas.</p>
				</td>
			</tr>
		</table>
	<h2>Abrir proyecto creado por libGDX</h2>
        <p>Menú principal->import project->elegir carpeta donde es guardado el proyecto generado por libGDX</p>
        <p>*Actualizar todo</p>
        <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen005.jpg?raw=true" width="75%" weight="auto"></p>
        <p>Cuando se tenga la siguiente pantalla estará listo para poder programar.</p>
        <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen006.jpg?raw=true" width="75%" weight="auto"></p>
    <h2>Ver en escritorio</h2>
        <p>Carpeta Desktop: dar clic derecho en DesktopLauncher y dar clic en Run 'DesktopLauncher.main()'</p>
        <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen007.jpg?raw=true" width="75%" weight="auto"></p>
        <p>Aparecerá un error, pero se habrá creado la configuración de DesktopLauncher.</p>
        <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen008.jpg?raw=true" width="75%" weight="auto"></p>
        <p>DesktopLauncher->Edit Configuration</p>
        <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen009.jpg?raw=true" width="75%" weight="auto"></p>
        <p>En el Working directory, escribir la ruta donde se encuentra la carpeta assets del proyecto. En general solo hay que agregar: \android\assets a lo que tenga por default.</p>
        <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen010.jpg?raw=true" width="75%" weight="auto"></p>
        <p>Al dar cilc en correr Aparecerá la siguiente pantalla, indicando que ya funciona.</p>
        <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen011.jpg?raw=true" width="75%" weight="auto"></p>
	<h2>Ver en Android</h2>
		<p>De acuerdo a la versión de Android, buscar la forma de activar la opción de programador. Dentro de las opciones de programador, en la seccion de depuración, activar la depuración por USB. También ponerlo en cargar dispositivo.</p>
		<p float="left">
			<img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen012.jpg?raw=true" width="50%" weigth="auto">
			<img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen013.jpg?raw=true" width="50%" weigth="auto">
		</p>
		<p>Elegir la opción de Android y dar clic en correr</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen014.jpg?raw=true" width="75%" weight="auto"></p>
		<p>Elegir el dispositivo y dar clic en OK</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen015.jpg?raw=true" width="75%" weight="auto"></p>
		<p>En la pantalla se mostrará en automático la siguiente pantalla</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen016.jpg?raw=true" width="75%" weight="auto"></p>
	<h1>Corrección de problemas</h1>
		<p>Eliminar "warning compile is obsolete"</p>
		<p>En la carpeta Gradle Scripts, el primer archivo build.gradle (Project prueba) cambiar todas las instrucciones "compile" con "implementation"</p>
		<p>Al borrar la clase por defecto y crear una nueva como principal, hay que modificar el AndroidLauncher y el DesktopLauncher.</p>
		<p>En Android->java->com.mygdx.prueba->AndroidLauncher modificar el parámetro de "initialize" con el nombre de la nueva clase.</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen017.jpg?raw=true" width="75%" weight="auto"></p>
		<p>En desktop->java->com.mygdx.prueba.desktop->DesktopLauncher modificar el parámetro de "LwjgIApplicationConfiguration" por el nombre de la nueva clase.</p>
		<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Instalacion_configuracion_correcion_de_problemas/imagen018.jpg?raw=true" width="75%" weight="auto"></p>
</body>
</html>
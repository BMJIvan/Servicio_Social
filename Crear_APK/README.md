<h1>Creación del APK</h1>
	<p>Cuando se crea el APK firmada hay un error al buscar un archivo. Para solucionarlo ir a Gradle Scripts->build.gradle (project prueba)->allprojects->repositories agregar jcenter(); tanbién se comentó mavenLocal() y mavenCentral().</p>
	<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen01.jpg?raw=true" width="75%" ></p>
<h2>Cambiar icono de aplicación</h2>
	<p>Ir a las carpetas y crear un nuevo Image Assest.</p>
	<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen02.jpg?raw=true" width="75%" ></p>
	<p>Se puede cargar la imagen y ver como se verá en los iconos. Se puede agregar un fondo.</p>
	<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen03.jpg?raw=true" width="75%" ></p>
	<p>Después ir a android->manifests->AndroidManifest.xml->sección aplication->android:icon=@ escribir la ubicación de la carpeta que contiene los iconos.</p>
	<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen04.jpg?raw=true" width="75%" ></p>
<h2>Cambiar nombre de app</h2>
	<p>Para evitar crear un nuevo proyecto para cada prueba, se modificarán 7 valores para que al instalar la nueva aplicación, esta no sustituya a la anterior, asi como También evitar errores al tener dos aplicaciones con el mismo identificador.</p>
	<table>
		<tr>
			<td>
				<p>En las carpetas del proyecto</p>
				<p>Android->manifests->AndroidManifest.xml</p>
				<p>Hay dos android_label, agregar el nombre de la aplicación entre comillas</p> 
			</td>
			<td>
				<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen05.jpg?raw=true" width="75%" ></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>El siguiente está en</p>
				<p>Core->generatedJava->com.mygdx.prueba->BuildConfig</p>
				<p>Cambiar el identificador de aplicación</p>
			</td>
			<td>
				<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen06.jpg?raw=true" width="75%" ></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>El siguiente se encuentra en</p>
				<p>Core->res->values->strings.xml</p>
				<p>Cambiar el nombre de la aplicación</p>
			</td>
			<td>
				<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen07.jpg?raw=true" width="75%" ></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>El siguiente se encuentra en</p>
				<p>Gradle Scripts->build.gradle(project:prueba)</p>
				<p>Cambiar el nombre de la aplicación</p>
			</td>
			<td>
				<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen08.jpg?raw=true" width="50%" ></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>El siguiente se encuentra en</p>
				<p>Gradle Scripts->build.gradle(Module:core)</p>
				<p>Cambiar el identificador de la aplicación</p>
			</td>
			<td>
				<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen09.jpg?raw=true" width="65%" ></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>Mas abajo aparece el ultimo</p>
				<p>Cambiar el identificador de la aplicación</p>
			</td>
			<td>
				<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Crear_APK/imagen10.jpg?raw=true" width="95%" ></p>
			</td>
		</tr>
	</table>
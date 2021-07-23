<h1>Prueba 1: Cubo impulsado por vector de fuerza</h1>
    <p>Guardar tamaño de la pantalla en variables. Hacer que la cámara considere un metro por cada 20 pixeles de pantalla, haccer que el mundo tenga gravedad cero y crear nuevo Body y Fixture para cada pared.</p>

```javascript
private Body ParedI,ParedD,ParedAr,ParedAb;
private Fixture ParedIFix, ParedDFix, ParedArFix,ParedAbFix;
private float W,H;
@Override
public void show() {
    W=Gdx.graphics.getWidth();
    H=Gdx.graphics.getHeight();
    world=new World(new Vector2(0,0),true);
    renderer=new Box2DDebugRenderer();
    camera=new OrthographicCamera(W/20,H/20);
}
```

   <p>En función show inicializar Body usando como parámetro una función. La cual se definirá fuera de la función show. "x" y "y" serán parámetros de la función y servirán para indicar la posición del centro y será del tipo estatico.</p>

```javascript
public void show() {
    BodyParedAb=world.createBody(createBodypareddef(0,-H/40+.25f));
}

private BodyDef createBodypareddef(float x,float y) {
    BodyDef def = new BodyDef();
    def.position.set(x, y);
    def.type= BodyDef.BodyType.StaticBody;
    return def;
}
```

   <p>Dentro de la función show, se crea la forma de un rectángulo del tamaño de los valores de largo y ancho de la pantalla menos un metro.</p>

```javascript
BodyParedAb=world.createBody(createBodypareddef(0,-H/40));
PolygonShape ParedShape=new PolygonShape();
ParedShape.setAsBox((W/40)-.5f,.25f); //el parámetro que recibe es la mitad de largo y ancho
ParedAbFix=BodyParedAb.createFixture(ParedShape,1f);// parámetro que recibe es forma y densidad
ParedAbFix.setRestitution(.7f);//modifica propiedad de restitución para hacer que el cubo rebote
ParedAbFix.setFriction(.3f);// se modifica la fricción para evitar que el cubo se detenga al chocar
```
   <p>En función dispose, eliminar el Body y la Fixture.</p>

```javascript
BodyPared.destroyFixture(ParedAbFix);
world.destroyBody(BodyParedAb);
```
   <p>Lo que se obtiene es un suelo.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba01_cubo_impulsado_por_vector_fuerza/imagen01.jpg?raw=true" width="75%"></p>
   <p>De la misma forma que se creó la paredAb, se van a crear las otras 3 paredes.</p>
   <p>Al finalizar la creación de la última pared en la función show usar</p>

```javascript
ParedShape.dispose();//se elimina ya que no se volverá a usar
```

```javascript
BodyParedAr=world.createBody(createBodypareddef(0f,+H/40-.25f));
ParedShape.setAsBox((W/40)-.5f,.25f);
ParedArFix=BodyParedAr.createFixture(ParedShape,1f);
ParedArFix.setRestitution(.7f);
ParedArFix.setFriction(.3f);

BodyParedD=world.createBody(createBodypareddef(W/40-.25f,0));
ParedShape.setAsBox(.25f,H/40);
ParedDFix=BodyParedD.createFixture(ParedShape,1f);
ParedDFix.setRestitution(.7f);
ParedDFix.setFriction(.3f);

BodyParedI=world.createBody(createBodypareddef(-W/40+.25f,0));
ParedShape.setAsBox(.25f,H/40);
ParedIFix=BodyParedI.createFixture(ParedShape,1f);
ParedIFix.setRestitution(.7f);
ParedIFix.setFriction(.3f);
ParedShape.dispose();
```

   <p>Después de crear las cuatro paredes se obtendrá lo siguiente:</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba01_cubo_impulsado_por_vector_fuerza/imagen02.jpg?raw=true" width="75%"></p>

   <p>Después se creará un cubo de un metro de lado, tipo dinámico, en el origen.</p>

```javascript
BodyPared=world.createBody(createBodycubodef());
    PolygonShape cuboShape=new PolygonShape();
    cuboShape.setAsBox(.5f,.5f);
    CuboFix=BodyCubo.createFixture(cuboShape,1f);
    CuboFix.setRestitution(.7f);
    CuboFix.setFriction(.3f);
    cuboShape.dispose();
}

private BodyDef createBodycubodef() {
    BodyDef def = new BodyDef();
    def.position.set(0, 0);
    def.type= BodyDef.BodyType.DynamicBody;
    return def;
}
```

   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba01_cubo_impulsado_por_vector_fuerza/imagen03.jpg?raw=true" width="75%"></p>
   <p>Siempre en la función dispose eliminar todos los Body y Fixture.</p>

```javascript
BodyParedAr.destroyFixture(ParedArFix);
world.destroyBody(BodyParedAr);
BodyParedI.destroyFixture(ParedIFix);
world.destroyBody(BodyParedI);
BodyParedD.destroyFixture(ParedDFix);
world.destroyBody(BodyParedD);
BodyCubo.destroyFixture(CuboFix);
world.destroyBody(BodyCubo);
```

   <p>Para hacer que el cubo se mueva primero se debe obtener la posición donde se tocó la patalla. Estos valores se usarán como parámetros de una función. Esta solo funcionará cuando se detecte un toque en la pantalla.</p>

```javascript
if(Gdx.input.justTouched()){
    impulso(Gdx.input.getX(),Gdx.input.getY());
}
```

   <p>Dentro de la función impulso se convertirá las coordenadas del cubo y se le restarán a las coordenadas de la posición donde se tocó la pantalla. Esto creará un vector desde el centro del cubo al punto donde se tocó la pantalla.</p>
   <p>Se dividirán las magnitudes en cada dirección entre 10 para evitar que tenga un impulso muy grande. Antes de recibir el impulso, se reiniciará su velocidad para que el cubo cambie por completo de dirección cuando la pantalla sea tocada. </p>

```javascript
private void impulso (float x,float y){
    float magx,magy;
    Vector2 position=BodyCubo.getPosition();
    magx=x-(position.x*20+W2);
    magy=y-(-position.y*20+H2);
    BodyCubo.setLinearVelocity(0,0);
    BodyCubo.applyLinearImpulse(magx/10,-magy/10,position.x,position.y,true);
}
```

   <p>Para la aplicación en Android se usará un factor de escala para hacer que el cubo aumente o disminuya su tamaño dependiendo del tamaño de la pantalla.</p>

```javascript
cuboShape.setAsBox(.5f+(((H/480)-1)*.5f),.5f+(((H/480)-1)*.5f));
```

   <p>Ya que el tamaño de pantalla podría cambiar el tamaño del cubo, su masa tambibién va a cambiar, así que se disminuirá la densidad del cubo con el factor de escala</p>

```javascript
CuboFix=BodyCubo.createFixture(cuboShape,1/(H/480));
```

   <p>Como extra se van a agregar dos objetos conformados por cadenas, uno con tipo dinámico y el otro estático, para comprobar que las colisiones solo pueden ocurrir cuando hay al menos un cuerpo con masa, ya que las cadenas no la poseen.</p>
    <p>Las cadenas solo son otro tipo de forma así que se aplicará la misma forma de definir los Body. La diferencia es que tendrá una forma hecha con base a puntos: esto permite colisiones en ambos lados y no tiene propiedades.</p>
    <p>Primero se agregará las variables de Body y Fixture para dos cadenas.</p>
    
```javascript
private Body BodyParedI,BodyParedD,BodyParedAr,BodyParedAb,Bodycadena1,Bodycadena2;
private Fixture ParedIFix, ParedDFix, ParedArFix,ParedAbFix,Cadena1Fix,Cadena2Fix;
```

   <p>Se creará un arreglo de vectores para guardar las posiciones de cada punto de la cadena, la cual se usará como parámetro para crear la forma de la cadena.</p>

```javascript
private Vector2 chain[];
```

   <p>Se crean los valores de cadena, se crea el Body y se le pasa el vector como parámetro en la forma para crear la cadena.</p>
   
```javascript
Vector2[] chain=new Vector2[7];
chain[0]=new Vector2(1f,0f);
chain[1]=new Vector2(3,2);
chain[2]=new Vector2(5,2);
chain[3]=new Vector2(7,0);
chain[4]=new Vector2(5,-2);
chain[5]=new Vector2(3,-2);
chain[6]=new Vector2(1,0);

Bodycadena1=world.createBody(createcedena1());
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
Cadena1Fix=Bodycadena1.createFixture(cadena,1);
```
                
```javascript
chain[0]=new Vector2(-7,0f);
chain[1]=new Vector2(-5,2);
chain[2]=new Vector2(-3,2);
chain[3]=new Vector2(-1,0);
chain[4]=new Vector2(-3,-2);
chain[5]=new Vector2(-5,-2);
chain[6]=new Vector2(-7,0);

Bodycadena2=world.createBody(createcedena2());
cadena=new ChainShape();
cadena.createChain(chain);
Cadena2Fix=Bodycadena2.createFixture(cadena,1);
cadena.dispose();
```
		    
   <p>En dispose se eliminan los nuevos cuerpos.</p>

```javascript
Bodycadena1.destroyFixture(Cadena1Fix);
world.destroyBody(Bodycadena1);
Bodycadena2.destroyFixture(Cadena2Fix);
world.destroyBody(Bodycadena2);
```

   <p>La función para definir tipo de cuerpo no necesita definir posición porque ya está dada cuando se crea la cadena.</p>

```javascript
private BodyDef createcedena1() {
    BodyDef def = new BodyDef();
    def.type= BodyDef.BodyType.DynamicBody;
    return def;
}

private BodyDef createcedena2() {
    BodyDef def = new BodyDef();
    def.type= BodyDef.BodyType.StaticBody;
    return def;
}
```

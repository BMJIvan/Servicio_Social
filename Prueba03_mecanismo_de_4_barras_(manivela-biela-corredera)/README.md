<h1>Tercera Prueba: Mecanismo de 4 barras (manivela-biela-corredera)</h1>
    <p>Primero de declaran las variables que se van a usar para crear el mundo, el ancho de la pantalla será equivalente a un metro.</p>

```javascript
float H,W,R,An,La;
```

   <p>Se crea el mundo en la función show.</p>

```javascript
An=1;
W=Gdx.graphics.getWidth();
H=Gdx.graphics.getHeight();
R=H/An;
La=W/R;
world=new World(new Vector2(0,-10),true);
renderer=new Box2DDebugRenderer();
camera=new OrthographicCamera(La,An);
```

   <p>Justo después se crean dos variables para la posición inicial del mecanismo.</p>

```javascript
float pox=-.45f;
float poy=0;
```

   <p>Se declaran en la función show usando las funciones para crear el Body y la Fixture.</p>

```javascript
CircleShape Crshape=new CircleShape();
PolygonShape Cushape=new PolygonShape();
Vector2[] vertices=new Vector2[3];
vertices[0]=new Vector2(-.05f,-.05f);
vertices[1]=new Vector2(0,.05f);
vertices[2]=new Vector2(.05f,-.05f);

BodyCr=world.createBody(createBody(pox,poy,'D'));
Crshape.setRadius(.2f);
CrFix=BodyCr.createFixture(createFix(Crshape,1,0,.3f));
BodyCr.setAngularDamping(.3f);

BodyTr=world.createBody(createBody(pox,poy,'S'));
Cushape.set(vertices);
TrFix=BodyTr.createFixture(createFix(Cushape,1,0,.1f));

BodyCu1=world.createBody(createBody(pox,poy,'D'));
Cushape.setAsBox(.4f,.0125f);
Cu1Fix=BodyCu1.createFixture(createFix(Cushape,1,0,1));
BodyCu1.setAngularDamping(.3f);

BodyCu2=world.createBody(createBody(pox,poy,'D'));
Cushape.setAsBox(.075f,.05f);
Cu2Fix=BodyCu2.createFixture(createFix(Cushape,1,0,.1f));
BodyCu2.setLinearDamping(.3f);

Cushape.dispose();
Crshape.dispose();
```

   <p>Cuando se crean los objetos, estos colisionan entre sí, por eso salen volando.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen01.jpg?raw=true" width="40%"></p>
   <p>Para evitar las colisiones se crea un grupo de filtro con un -1, lo cual significa que ningún cuerpo en ese grupo podrá colisionar entre sí.</p>
   <p>*si el valor es igual o mayor a cero podrán colisionar.</p>

```javascript
Filter fil=new Filter();
fil.groupIndex=-1;
```

   <p>Se asigna el grupo a los cuerpos.</p>

```javascript
TrFix.setFilterData(fil);
CrFix.setFilterData(fil);
Cu1Fix.setFilterData(fil);
Cu2Fix.setFilterData(fil);
```

   <p>En este punto ya no colisionan, solo caen al vacío.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen02.jpg?raw=true" width="40%"></p>
   <p>Se crean las juntas en la función show.</p>

```javascript
JoinPr=(PrismaticJoint) world.createJoint(createPrJoin(BodyTr,BodyCu2,0,0,-.15f,0,false,true,false,0,0,1,1,0));
JoinRev1=(RevoluteJoint) world.createJoint(createRevjoin(BodyTr,BodyCr,0,0,0,0,false,false,0,0));
JoinRev2=(RevoluteJoint) world.createJoint(createRevjoin(BodyCr,BodyCu1,-.18f,0,-.38f,0,false,false,0,0));
JoinRev3=(RevoluteJoint) world.createJoint(createRevjoin(BodyCu1,BodyCu2,.38f,0,-.07f,0,false,false,0,0));
```

   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen03.jpg?raw=true" width="50%"></p>
   <p>Al no tener fricción tardará mucho tiempo en detenerse, así que se les asignará fricción angular a los tres cuerpos dinámicos.</p>

```javascript
BodyCu2.setLinearDamping(.5f);
BodyCu1.setAngularDamping(.5f);
BodyCr.setAngularDamping(.5f);
```

   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen04.jpg?raw=true" width="50%"></p>
   <p>Se agregan variables para el setpoint, error, error anterior, suma de errores, par de torsión, Kp (ganancia proporcional), Kd (ganancia derivativa), Ki (ganancia integrativa),</p>

```javascript
private  float sp,e,ean,ein,par,kp,kd,ki;
```

   <p>Se obtiene el setpoint (sp) con la función de cambio de coordenadas y se usa un saturador para evitar que tome valores que no se puedan alcanzar. El valor del setpoint se multiplica por 100 para que este en centímetros.</p>

```javascript
sp=Pix2Box(Gdx.input.getX())*100;
if(sp<=20) { sp=20; }
else if(sp>=56) { sp=56;}
```

   <p>Se añade una variable booleana para indicar que el controlador funcionará cuando se toque la pantalla.</p>

```javascript
private boolean Con;
```

   <p>Se inicializa en la función show.</p>

```javascript
Con=false;
```

   <p>Se define el error, la ganancia Kp y se crea el controlador proporcional. La posición se multiplica por 100 para obtener el valor en centímetros.</p>

```javascript
e=sp-BodyCu2.getPosition().x*100;
kp=.004f;
if(Gdx.input.justTouched()) { Con=true;}
if(Con) {
    par = (kp * e); }
par=(e*kp);
BodyCr.applyTorque(par,true);
```

   <p>No alcanza el máximo valor, y además oscila.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen05.jpg?raw=true" width="60%"></p>
   <p>Así que se agrega la ganancia Kd y es agregada al controlador.</p>

```javascript
kd=.004f;
par = (kp * e)+((kd*(e-ean)/delta)); 
```

   <p>Se guarda el error anterior con el error junto después del render.</p>

```javascript
renderer.render(world,camera.combined);
ean=e;
```

   <p>Sigue sin alcanzar el objetivo pero oscila menos.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen06.jpg?raw=true" width="60%"></p>
   <p>Se agrega la ganancia Ki, un sumador del error y se agrega al controlador.</p>

```javascript
ki=.004f;
ein=ein+e*delta;
par = (kp * e)+((kd*(e-ean)/delta))+(ki*ein);
```

   <p>Ya puede alcanzar al sp pero hace falta restringir el valor del ángulo de la manivela.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen07.jpg?raw=true" width="60%"></p>
   <p>Se crea una variable para guardar el ángulo.</p>

```javascript
ang=BodyCr.getAngle();
```

   <p>Cuando el ángulo este fuera de los límites se aumentará la fricción para evitar que siga avanzando, y se le aplicará un par de torsión para tratar de regresarlo.</p>

```javascript
if(ang<-.02) {
    BodyCr.setAngularDamping(10000);
    BodyCr.applyTorque(.01f,true);
}else if (ang>=Math.PI+.01){
    BodyCr.setAngularDamping(1000);
    BodyCr.applyTorque(-.01f,true);}
    else{
    BodyCr.setAngularDamping(.3f);}
```

   <p>Por último se agregará texto para informar la distancia a la que está el bloque del centro de la manivela.</p>
   <p>Se agregan variables para mostrar texto.</p>

```javascript
private BitmapFont font;
private SpriteBatch batch;
```

   <p>Se definen en la función show, se le asigna el factor de escala a Font.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/480);
```

   <p>Se agregan las varables para guardar las coordenadas donde se mostrará y para guardar el texto.</p>

```javascript
private float corx,cory;
private String str;
```

   <p>Se declara el formato.</p>

```javascript
DecimalFormat df = new DecimalFormat("#0.00");
```

   <p>Se usan las funciones de cambio de coordenadas para ubicar el texto sobre la corredera.</p>

```javascript
corx=Box2Pix(BodyCu2.getPosition().x-.15f);
cory=Boy2Piy(BodyCu2.getPosition().y+.1f);
```

   <p>Se calcula la distancia y se imprime el texto en la pantalla.</p>

```javascript
str=df.format(((BodyCu2.getPosition().x)*100)+45);
batch.begin();
font.draw(batch,"Distancia = "+str,corx,cory);
batch.end();
```

   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba03_mecanismo_de_4_barras_(manivela-biela-corredera)/imagen08.jpg?raw=true" width="60%"></p>
   <p>Eliminar todo lo que se usó en función dispose.</p>
   <p>Para la aplicación en Android se agregarán dos funciones en la clase principal para que la aplicación se reinicie cuando se salga y vuelva a entrar en ella.</p>

```javascript
@Override
 public void resume() {
     setScreen(Pan1);
 }

 @Override
 public void pause() {
     Pan1.dispose();
 }
```

   <p>Además, se va a modificar la función render de la pantalla de Box2dScreen para que el primer frame no se muestre, esto debido a que los objetos son creados en el mismo lugar al principio, es decir, se puede ver cómo pasan de su posición inicial a formar el mecanismo. Primero se declara la variable.</p>

```javascript
private boolean on;
```

   <p>Se iniciliza en la función show.</p>

```javascript
on=false;
```

   <p>Todo el código se pone dentro de un if, se permite que ocurra un frame, y se cambia el valor de on para que entre al código principal.</p>

```javascript
if(on)
{
//todo el codigo
}else {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    world.step(delta,6,2);
    camera.update();
    renderer.render(world,camera.combined);
    on=true;
}
```
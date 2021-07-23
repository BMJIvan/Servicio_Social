<h1>Prueba 8 experimento 5 y 6 impulso de velocidad y restitución</h1>
    <p>Para aplicar un impulso de velocidad se usa la siguiente ecuación.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}V=\frac{V}{m}"></p>
    <p>En Box2d se usaría.</p>

```javascript
Body.setLinearVelocity(Body.getMass()*Vx,Body.getMass()*Vy);
```

   <p>Donde la m es la masa del objeto, por lo que si se quiere que el objeto tenga una velocidad inicial en especifico, siempre se debe multiplicar el valor del impulso por la masa.</p>
   <p>La restitución permite que un objeto pierda velocidad cuando se realiza una colisión.</p>
   <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}V=Vr"></p>
   <p>Donde r puede tomar un valor mayor o igual a 0. La restitución solo afecta al propio objeto, significa que, aunque un objeto con restitución de 1, choque con uno de restitución 0, el objeto con restitución de 1 seguirá moviéndose y el de 0 se detendrá.</p>
<h2>En código</h2>
   <p>Se crean las variables que se van a usar.</p>

```javascript
float H,W,R,An,La;

private Body BodyCu1,BodyCu2,BodySu;
private Fixture Cu1Fix,Cu2Fix,SuFix;

private BitmapFont font;
private SpriteBatch batch;

private String V1,V2,V10,V20;
private  float t,vel1,vel2,vel10,vel20;
```

   <p>Se crea el mundo, el ancho de la pantalla será de 10m.</p>

```javascript
An=10;
W=Gdx.graphics.getWidth();
H=Gdx.graphics.getHeight();
R=H/An;
La=W/R;
world=new World(new Vector2(0,-10),true);
renderer=new Box2DDebugRenderer();
camera=new OrthographicCamera(La,An);
```

   <p>Se inicializa el Font y se modifica la escala.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/360);
```

   <p>Se crea un suelo con un valor de restitución de 0.</p>

```javascript
Vector2[] chain=new Vector2[2];
chain[0]=new Vector2(-La/2,-5);
chain[1]=new Vector2(La/2,-5);

BodySu=world.createBody(createBody(0,0,'S'));
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
SuFix=BodySu.createFixture(createFix(cadena,0,0,0));
```

   <p>Se crearán dos cubos de diferente tamaño, pero con un valor de restitución diferente.</p>

```javascript
PolygonShape Cushape=new PolygonShape();
BodyCu1=world.createBody(createBody(-2,-4.5f,'D'));
Cushape.setAsBox(.5f,.5f);
Cu1Fix=BodyCu1.createFixture(createFix(Cushape,1,0,.9f));
BodyCu2=world.createBody(createBody(2,-3.5f,'D'));
Cushape.setAsBox(1.5f,1.5f);
Cu2Fix=BodyCu2.createFixture(createFix(Cushape,1,0,1));
Cushape.dispose();
```

   <p>Se bloquea la rotación.</p>

```javascript
BodyCu1.setFixedRotation(true);
BodyCu1.setFixedRotation(true);
```

   <p>Se crea el formato para convertir a texto.</p>

```javascript
DecimalFormat df = new DecimalFormat("#0.00");
```

   <p>En la función render se agrega la ecuación de tiempo al final.</p>

```javascript
t=t+.01f;
```

   <p>Se agrega una condición para que el impulso solo funcione una vez. Al primer objeto se le dará un impulso multiplicando por su masa y al otro solo se escribirá el valor.</p>

```javascript
if(t==0) {
    BodyCu1.applyLinearImpulse(0, 15* BodyCu1.getMass(), BodyCu1.getLocalCenter().x, BodyCu1.getLocalCenter().y, true);
    BodyCu2.applyLinearImpulse(0, 60, BodyCu2.getLocalCenter().x, BodyCu2.getLocalCenter().y, true);
}
```

   <p>Se guardará la velocidad inicial dentro del condicional anterior.</p>

```javascript
vel10=BodyCu1.getLinearVelocity().y;
V10=df.format(vel10);
vel20=BodyCu2.getLinearVelocity().y;
V20=df.format(vel20);
```

   <p>Se guarda la velocidad de los dos objetos.</p>

```javascript
vel1=BodyCu1.getLinearVelocity().y;
vel2=BodyCu2.getLinearVelocity().y;

V1=df.format(vel1);
V2=df.format(vel2);
```

   <p>Se mostrará el valor de velocidad de cada objeto.</p>

```javascript
batch.begin();
font.draw(batch,"velocidad = "+V1,Box2Pix(-6),Boy2Piy(1.5f));
font.draw(batch,"Velocidad = "+V2,Box2Pix(2.5f),Boy2Piy(1.5f));
font.draw(batch,"velocidad inicial ="+V10,Box2Pix(-6),Boy2Piy(1));
font.draw(batch,"velocidad inicial ="+V20,Box2Pix(2.5f),Boy2Piy(1));
batch.end();
```

   <p>En el caso del primer objeto, el impulso se está multiplicando por la masa por eso la velocidad inicial es de 15. En el caso del segundo objeto, se le dio un impulso de 60, pero al dividir ese valor entre su masa (9kg) se obtuvo 6.66. Ambas son las velocidades que se obtuvierón en la simulación.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba08_experimento_5_y_6_impulso_de_velocidad_y_restitucion/imagen01.jpg?raw=true" width="60%"></p>
   <p>En el caso de la restitución, el primer cubo tiene un valor de 0.9. Lo cual significa que poco a poco perderá velocidad hasta quedarse completamente estático. En cambio el segundo tiene un valor de restitución de uno, lo cual significa que no perderá velocidad y seguirá rebotando por siempre. También se puede comprobar que aunque el suelo tiene una restitución de 0, no está afectando a los dos cuerpos.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba08_experimento_5_y_6_impulso_de_velocidad_y_restitucion/imagen02.jpg?raw=true" width="60%"></p>
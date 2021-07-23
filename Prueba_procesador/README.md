<h1>Prueba Procesador</h1>
    <p>Primero se declaran las variables que se van a usar para crear el mundo, el ancho de la pantalla será equivalente a 10 metros y no hrbrá aceleración debido a la gravedad.</p>
    
```javascript
float H,W,R,An,La;
```

    <p>Se declaran los cuerpos y variables para mostrar texto.</p>

```javascript
private Body BodyCa;
private Fixture CaFix;

private BitmapFont font;
private SpriteBatch batch;
```

    <p>Se crea variables para guardar posición.</p>

```javascript
private float borde;
private float bordeRan;
```

    <p>Se crean dos listas, una para guardar los fixture y otra para los body.</p>


```javascript
private Array<Body> bodiesW = new Array<Body>();
private Array<Fixture>  fixturesW=new Array<Fixture>(); 
```

    <p>Se crea el mundo, la variable borde guardará la posición donde comenzaran a caer las pelotas.</p>

```javascript
An=10;
W=Gdx.graphics.getWidth();
H=Gdx.graphics.getHeight();
R=H/An;
La=W/R;
world=new World(new Vector2(0,0),true);
renderer=new Box2DDebugRenderer();
camera=new OrthographicCamera(La,An);
borde=-((La/2)-.5f);
t=0;
```

    <p>Se inicializan variables para mostrar textos, se aumenta la escala al texto.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/360);
```

    <p>Se crea un contorno en la pantalla, usando una cadena, se usan funciones hechas anteriormente.</p>

```javascript
PolygonShape Cushape=new PolygonShape();
Vector2[] chain=new Vector2[5];
chain[0]=new Vector2(-La/2+.01f,-5+.01f);
chain[1]=new Vector2(-La/2+.01f,4);
chain[2]=new Vector2(La/2,4);
chain[3]=new Vector2(La/2,-5+.01f);
chain[4]=new Vector2(-La/2+.01f,-5+.01f);

BodyCa=world.createBody(createBody(0,0,'S'));
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
CaFix=BodyCa.createFixture(createFix(cadena,0,0,1));
Cushape.dispose();
```

    <p>El contorno deja un espacio en la parte de arriba para poder mostrar texto.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_procesador/imagen01.jpg?raw=true" width="60%"></p>
    <p>Se declaran variables para el tiempo, temporizador y un contador.</p>

```javascript
private float t=0,tn=0;
private int i=0;
```

    <p>Se declaran variables que se usaran para mostrar texto.</p>

```javascript
private String fps,tiempo,cuerpos;
```

    <p>Al final de la función render agregar el tiempo y el temporizador.</p>
    
```javascript
t=t+delta;
tn=tn-delta;
```

    <p>Se van a crear cuerpos cada vez que el temporizador llegue a cero. Se dejarán de crear cuerpos si los FPS son menores a 40.</p>

```javascript
if(tn <= 0&&(1/delta)>=40){
}
```

    <p>Dentro de la función se actualizará los FPS y se reiniciará el temporizador.</p>

```javascript
fps=df.format(1/delta);
tn=.15f;
```

    <p>Después se agregará un cuerpo a la lista, este aparecerá de manera aleatoria pero cerca del borde.</p>

```javascript
CircleShape Crshape=new CircleShape();
bordeRan=borde+((float) Math.random()*.7f);
bodiesW.add(world.createBody(createBody(bordeRan,((float)Math.random()*.25f+3.5f),'D')));
Crshape.setRadius(.125f);
fixturesW.add(bodiesW.get(i).createFixture(createFix(Crshape,(float)(1/(Math.pow(.5f,2)*Math.PI)),0,.99f)));
Crshape.dispose();
```

    <p>Y al final de la función se estará guardando el número de cuerpos creados.</p>

```javascript
i=i+1;
```

    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_procesador/imagen02.jpg?raw=true" width="60%"></p>
    <p>Se declara un formato de texto.</p>

```javascript
DecimalFormat df = new DecimalFormat("#0.00");
```

    <p>Se guardan los datos que se quieren mostrar.</p>

```javascript
tiempo=df.format(t);
fps=df.format(1/delta);
cuerpos=df.format(i);
```

    <p>Por último, se muestra en pantalla los datos.</p>

```javascript
batch.begin();
font.draw(batch,"FPS = "+fps,Box2Pix(-6),Boy2Piy(4.5f));
font.draw(batch,"Tiempo = "+tiempo,Box2Pix(-2),Boy2Piy(4.5f));
font.draw(batch,"Cuerpos = "+cuerpos,Box2Pix(2),Boy2Piy(4.5f));
batch.end();
```

    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_procesador/imagen03.jpg?raw=true" width="60%"></p>
<h1>Prueba 4: experimento 1 fricción (estático)</h1>
    <p>Formula de fricción en Box2d.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\mu_{requiere}=\sqrt{\mu_{cuerpoA}*\mu_{cuerpoB}}"></p>
    <p>Si se conoce la fricción de un cuerpo y la requerida, se puede despejar una de las fricciones.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\mu_{requiere}^2=\mu_{cuerpoA}*\mu_{cuerpoB}"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\mu_{cuerpoB}=\frac{\mu_{requiere}^2}{\mu_{cuerpoA}}"></p>
    <p>Si la fricción del cuerpo A se considera 1, entonces:</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\mu_{cuerpoB}=\mu_{requiere}^2"></p>
    <p>Ejemplo: fuerza necesaria para mover un cubo de 10N de peso con un coeficiente de fricción de 0.6.</p>
    <p>La fuerza necesaria para que el objeto comience a moverse es:</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}F=mg\mu=W\mu"></p>
    <p>Así que la fuerza mínima para que comience a moverse en 6N.</p>
<h2>En código</h2>
    <p>Crear las variables.</p>

```javascript
float H,W,R,An,La;

private Body BodyCu,BodySu;
private Fixture CuFix,SuFix;

private BitmapFont font;
private SpriteBatch batch;

private String Fuerza,Velocidad;
private float F=0,V=0;
```

   <p>En la función show, se crea el mundo considerando que el ancho de la pantalla equivale a 5m.</p>

```javascript
An=5;
W=Gdx.graphics.getWidth();
H=Gdx.graphics.getHeight();
R=H/An;
La=W/R;
world=new World(new Vector2(0,-10),true);
renderer=new Box2DDebugRenderer();
camera=new OrthographicCamera(La,An);
```

   <p>Se inicializan las variables de texto.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/360);
```

   <p>Se crea el suelo usando una cadena de dos puntos, se le da un valor de fricción de 1.</p>

```javascript
Vector2[] chain=new Vector2[2];
chain[0]=new Vector2(-La/2,-.5f);
chain[1]=new Vector2(La/2,-.5f);

BodySu=world.createBody(createBody(0,0,'S'));
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
SuFix=BodySu.createFixture(createFix(cadena,0,1,0));
```

   <p>Se crea un cubo, se usa la fórmula del cuerpo B para aplicar el valor de fricción, es decir, se eleva al cuadrado 0.6.</p>

```javascript
PolygonShape Cushape=new PolygonShape();
BodyCu=world.createBody(createBody(-2,0,'D'));
Cushape.setAsBox(.5f,.5f);
CuFix=BodyCu.createFixture(createFix(Cushape,1,(float)Math.pow(.6f,2),0));
Cushape.dispose();
```

<h2>En función render</h2>
   <p>Se aplica fuerza en el centro del cubo y se guarda la velocidad en una variable.</p>

```javascript
BodyCu.applyForceToCenter(F,0,true);
V=BodyCu.getLinearVelocity().x;
```

   <p>Se crea un formato de texto, se convierten las variables de fuerza y velocidad.</p>

```javascript
DecimalFormat df = new DecimalFormat("#0.00");
Fuerza=df.format(F);
Velocidad=df.format(V);
```

   <p>Se muestra en pantalla la fuerza y velocidad.</p>

```javascript
batch.begin();
font.draw(batch,"Fuerza = "+Fuerza,Box2Pix(1),Boy2Piy(1.5f));
font.draw(batch,"Velocidad = "+Velocidad,Box2Pix(-2),Boy2Piy(1.5f));
batch.end();
```

   <p>Se crea un condicional al final de la función render, para que siga aumentando la fuerza mientras no se mueva.</p>

```javascript
if(V < .0001)
{
    F=F+.01f;
}
```

   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba04_experimento_1_friccion_(estatico)/imagen01.jpg?raw=true" width="60%"></p>
<h1>Prueba 5 experimento 2 fricción (dinámico)</h1>
    <p>La fuerza mínima para que un cuerpo se mueva debe ser mayor a la fuerza de fricción. Es entonces que se aplica un modelo dinámico.</p>
    <p>Se considera un cubo al que se le aplica una fuerza, tiene un peso y una fuerza de fricción.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba05_experimento_2_friccion_(dinamico)/imagen01.jpg?raw=true" width="60%"></p>
    <p>Debido a que es una masa, solo se necesita una ecuación.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}m\ddot{X}+F_{r}=F"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}F_{r}=W\mu"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}m\ddot{X}=F-F_{r}=F-W\mu"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\ddot{X}=\frac{F-W\mu}{m}"></p>
    <p>Se debe integrar dos veces.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\dot{X}=\frac{F-W\mu}{m}t+v(0)"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\dot{X}=\frac{F-W\mu}{2m}t^2+v(0)t+x(0)"></p>
    <p>Se usará el ejemplo anterior, pero ahora se cambiará la fuerza a 7N con una posición inicial de -2.5.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}X=\frac{7-6}{12}t2-2.5"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}X=0.5^2-3"></p>
<h2>En código</h2>
    <p>Crear las variables, se usará los gráficos y dos listas para guardar la trayectoria que se obtiene del cuerpo y la que se obtiene por ecuaciones.</p>

```javascript
float H,W,R,An,La;

private Body BodyCu,BodySu;
private Fixture CuFix,SuFix;

private BitmapFont font;
private SpriteBatch batch;

private float F=7,t=0,X=0;
private ShaderProgram shader;
private Mesh mesh;
private String str,pos1,pos2;

private List<Float> graftra;
private List<Float> grafecu;
```

   <p>Inicializar variables de mundo en función show</p>

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

   <p>Se crea un suelo y el cubo de la misma forma que en el anterior experimento, pero se incrementa la longitud del suelo.</p>

```javascript
Vector2[] chain=new Vector2[2];
chain[0]=new Vector2(-La/2,-.5f);
chain[1]=new Vector2(10*(La/2),-.5f);

BodySu=world.createBody(createBody(0,0,'S'));
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
SuFix=BodySu.createFixture(createFix(cadena,0,1,0));

PolygonShape Cushape=new PolygonShape();
BodyCu=world.createBody(createBody(-2.5f,0,'D'));
Cushape.setAsBox(.5f,.5f);
CuFix=BodyCu.createFixture(createFix(Cushape,1,(float)Math.pow(.6f,2),0));
Cushape.dispose();
```

   <p>*se están usando las funciones createbody y createfix antes hechas.</p>
   <p>Se inicializan las variables de gráficos y listas.</p>
```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/360);
str=FragShader();
ShaderProgram.pedantic=false;

graftra=new ArrayList<Float>();
grafecu=new ArrayList<Float>();
graftra.clear();
grafecu.clear();
```

   <p>*se están usando las funciones FragShader y VertShader antes hechas.</p>
   <p>En la función render se limpia la pantalla y se crea el formato de texto.</p>

```javascript
Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
Gdx.gl.glClearColor(0.1f,0f,0.1f,0.5f);
DecimalFormat df = new DecimalFormat("#0.00");
```

   <p>Se aplica una fuerza en el centro del cubo de 7N y se guardan las posiciones del objeto.</p>

```javascript
BodyCu.applyForceToCenter(F,0,true);
graftra.add(BodyCu.getPosition().x);
graftra.add(BodyCu.getPosition().y);
```

   <p>Se escribe la ecuación que se obtuvo antes, se guardan los datos considerando que la altura se mantiene constante.</p>

```javascript
X=((t*t)/2)-2.5f;
grafecu.add(X);
grafecu.add(.1f);
```

   <p>Se usa una función para graficar. La gráfica que se obtiene del cuerpo se queda en su lugar, y la que se obtiene por medio de la ecuación se traslada un poco arriba.</p>

```javascript
plot(graftra,0,0,1,1,1,0,0,.5f,true);
plot(grafecu,0,0,1,1,0,1,0,.5f,true);
```

   <p>*se usa la función plot antes hecha en la prueba de gráficos</p>
   <p>Se guarda los valores de posición en dos variables y se obtiene el error.</p>

```javascript
pos1=df.format(BodyCu.getPosition().x);
pos2=df.format(X);
error=df.format(Math.abs(BodyCu.getPosition().x-X));
```

   <p>Se muestran las variables de posición y el error.</p>

```javascript
batch.begin();
font.draw(batch,"Posicion cuerpo  ="+pos1,Box2Pix(-2),Boy2Piy(2.2f));
font.draw(batch,"Posicion ecuacion="+pos2,Box2Pix(-2),Boy2Piy(1.7f));
font.draw(batch,"           error ="+error,Box2Pix(-2),Boy2Piy(1.2f));
batch.end();
```

   <p>Al final de la función render agregar la ecuación del tiempo.</p>

```javascript
t=t+delta;
```

   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba05_experimento_2_friccion_(dinamico)/imagen02.jpg?raw=true" width="60%"></p>
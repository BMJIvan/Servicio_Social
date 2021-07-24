<h1>Prueba 9 experimento 7 tiro parabólico</h1>
    <p>En el ttiro parabólico se considera que hay dos ecuaciones, una que es paralela al suelo, la cual no es afectada por la aceleración de la gravedad, la segunda es perpendicular al suelo y si es afectada por la gravedad.</p>
    <p>Se va a considerar un cubo de un metro de lado, va a recibir un impulso de velocidad en el centro.</p>
    <p>El eje x se considerará como el eje paralelo, por lo tanto, la aceleración en x es cero. Se considerará que se parte del reposo. A partir de esto se obtiene la siguiente ecuación.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\ddot{x}=0"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\dot{x}=vx"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\ddot{x}=vxt+x_{0}"></p>
    <p>El eje y se considera como el eje perpendicular al eje x, por lo tanto su aceleración será igual a -g. A partir de esto se obtienen la siguiente ecuación.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\ddot{y}=-g"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\dot{y}=-gt+vy"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}\ddot{y}=-g\frac{t^2}{2}+vyt+y_{0}"></p>
<h2>En código</h2>
    <p>Se crea el mundo, se crea la variable de gravedad con un valor de 10, se van a usar dos cuerpos, uno es el suelo y el otro es el cuerpo al que se le aplicara un impulso de velocidad.</p>

```javascript
float H,W,R,An,La,g=10;

private Body BodyCu,BodySu;
private Fixture CuFix,SuFix;
```

    <p>Se crean las variables para guardar y mostrar la velocidad. También se guarda la posición inicial del cuerpo.</p>

```javascript
private String Vx,Vy,VR,Vxs,Vys,VRs;
private  float t,velx,vely,velr,velxs,velys,velrs,pxo,pyo,tf;
```

    <p>Se crean dos listas para graficar.</p>

```javascript
private List<Float> grafica;
private List<Float> graficaf;    
```

    <p>Rn función show, inicializar el mundo.</p>

```javascript
An=10;
W=Gdx.graphics.getWidth();
H=Gdx.graphics.getHeight();
R=H/An;
La=W/R;
world=new World(new Vector2(0,-g),true);
renderer=new Box2DDebugRenderer();
camera=new OrthographicCamera(La,An);
```

    <p>Se crea el suelo usando una cadena.</p>

```javascript
Vector2[] chain=new Vector2[5];
chain[0]=new Vector2(-La/2,2.5f);
chain[1]=new Vector2((-La/2)+5,2.5f);
chain[2]=new Vector2((-La/2)+5,(-An/2)+.1f);
chain[3]=new Vector2(La/2-.1f,(-An/2)+.1f);
chain[4]=new Vector2(La/2-.1f,2.5f);

BodySu=world.createBody(createBody(0,0,'S'));
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
SuFix=BodySu.createFixture(createFix(cadena,0,0,0));
```

    <p>Se le da una posición inicial en la esquina superior izquierda, se evita que gire al usar setFixedRotation como true.</p>

```javascript
pxo=(-La/2)+1;
pyo=3;
PolygonShape Cushape=new PolygonShape();
BodyCu=world.createBody(createBody(pxo,pyo,'D'));
Cushape.setAsBox(.5f,.5f);
CuFix=BodyCu.createFixture(createFix(Cushape,1,0,0));
Cushape.dispose();
BodyCu.setFixedRotation(true);
```

    <p>Se inicializa las variables de gráficos y se limpian las listas.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/360);

str=FragShader();
ShaderProgram.pedantic=false;

grafica=new ArrayList<Float>();
graficaf=new ArrayList<Float>();
grafica.clear();
graficaf.clear();
```

    <p>*se deben usar las funciones FragShader y VertShader.</p>
    <p>Así queda el escenario.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba09_experimento_7_tiro_parabolico/imagen01.jpg?raw=true" width="60%"></p>
    <p>En la función render</p>
    <p>Se limpia la pantalla y se crea un formato de texto.</p>

```javascript
Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
Gdx.gl.glClearColor(0.1f, 0f, 0.1f, 0.5f);
DecimalFormat df = new DecimalFormat("#0.00");
```

    <p>Se le da un valor de velocidad inicial.</p>

```javascript
if(t==0) {
    float masa=BodyCu.getMass();
    BodyCu.setLinearVelocity(5*masa,5*masa);
}
```

    <p>Se guarda los valores de velocidad del cuerpo, y se usan las ecuaciones obtenidas al principio.</p>

```javascript
velx=BodyCu.getLinearVelocity().x;
vely=BodyCu.getLinearVelocity().y;
velr=(float)Math.sqrt(Math.pow(velx,2)+Math.pow(vely,2));
velxs=5;
velys=(-g*t)+(5);
velrs=(float)Math.sqrt(Math.pow(velxs,2)+Math.pow(velys,2));
```

    <p>Se convierten los valores de velocidad a string.</p>

```javascript
Vx=df.format(velx);
Vy=df.format(vely);
VR=df.format(velr);
Vxs=df.format(velxs);
Vys=df.format(velys);
VRs=df.format(velrs);
```

    <p>Se guarda los valores de posición en las listas, se usan las ecuaciones del principio.</p>

```javascript
grafica.add(BodyCu.getPosition().x);
grafica.add(BodyCu.getPosition().y);
graficaf.add(5*t+pxo);
graficaf.add(((-g * t * t) / 2) + (5 * t) + pyo);
```

    <p>Se grafican usando la función plot que se creó antes.</p>

```javascript
plot(grafica,0,0,1,1,1,0,0,1,true);
plot(graficaf,0,0,1,1,0,1,0,1,true);
```

    <p>Se muestran en pantalla las velocidades.</p>

```javascript
batch.begin();
font.draw(batch,"velocidad x= "+Vx,Box2Pix((-La/2)+.5f),Boy2Piy(2f));
font.draw(batch,"Velocidad y= "+Vy,Box2Pix((-La/2)+.5f),Boy2Piy(1.5f));
font.draw(batch,"velocidad R= "+VR,Box2Pix((-La/2)+.5f),Boy2Piy(1));
font.draw(batch,"velocidad xs= "+Vxs,Box2Pix((-La/2)+.5f),Boy2Piy(.5f));
font.draw(batch,"velocidad ys= "+Vys,Box2Pix((-La/2)+.5f),Boy2Piy(0));
font.draw(batch,"velocidad Rs= "+VRs,Box2Pix((-La/2)+.5f),Boy2Piy(-.5f));
batch.end();
```

    <p>Se agrega la ecuación del tiempo el final de la función render.</p>

```javascript
t=t+delta;
```

    <p>Así se ve la aplicación después de que el cubo se detiene.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba09_experimento_7_tiro_parabolico/imagen02.jpg?raw=true" width="60%"></p>
    <p>*la aplicación sigue calculando después de que el cubo se detiene, así que se van a hacer algunas modificaciones al código.</p>
    <p>Se evitará que la gráfica funcione como si el cuerpo estuviera cayendo, cuando el cubo reduzca su velocidad en y a 0, la altura de la gráfica se hará constante. Así que se modifica la ecuación del guardado de la posición de la trayectoria.</p>

```javascript
if(Math.abs(BodyCu.getLinearVelocity().y)>=0.01) {
    graficaf.add(((-g * t * t) / 2) + (5 * t) + pyo);
    tf=t+delta;
}else
{
    graficaf.add(((-g * tf * tf) / 2) + (5 * tf) + pyo);
}
```

    <p>Después de agregar el código anterior, la trayectoria se ve mas parecida a la del cubo, pero la velocidad en x sigue funcionando aun después de que el cubo se detiene.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba09_experimento_7_tiro_parabolico/imagen03.jpg?raw=true" width="60%"></p>
    <p>Se va a modificar la línea de obtención de velocidad en x.</p>

```javascript
if(Math.abs(BodyCu.getLinearVelocity().x)>.01) {
    velxs = 5;
}else
{
    velxs=0;
}
```

    <p>De la misma forma se va a modificar la línea de obtención del cálculo de la velocidad en y.</p>

```javascript
if(Math.abs(BodyCu.getLinearVelocity().y)>.01) {
    velys=(-g*t)+(5);
}else
{
    velys=0;
}
```

    <p>Por último se va a modificar la línea de obtención de la posición en x.</p>

```javascript
if(Math.abs(BodyCu.getLinearVelocity().x)>=0.01) {
    graficaf.add(5*t+pxo);

    tfn=t+delta;
}else
{
    graficaf.add(5*tfn+pxo);
}
```
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba09_experimento_7_tiro_parabolico/imagen04.jpg?raw=true" width="60%"></p>
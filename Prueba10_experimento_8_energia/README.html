<h1>Prueba 10 experimento 8 Energía</h1>
    <p>Se van  a usar las ecuaciones para la Energía cinética y potencial.</p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}E_{c}=\frac{1}{2}mv^2"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}E_{p}=mgh"></p>
    <p align="center"><img src="https://latex.codecogs.com/svg.latex?\Large&space;\color{White}E_{t}=E_{c}+E_{p}"></p>
    <p>Donde m es la masa, v es el absoluto de la velocidad, g es la gravedad, y h es la altura del objeto respecto al suelo.</p>

<h2>En código</h2>
    <p>Primero se crean las variables que se van a usar.</p>

```javascript
float H,W,R,An,La,g=10;

private Body BodyCr,BodySu;
private Fixture CrFix,SuFix;

private ShaderProgram shader;
private BitmapFont font;
private SpriteBatch batch;
private Mesh mesh;
private String vtr,str;

private String Ecs,Eps,Ets;
private  float t,velx,vely,velr,Ec,Ep,Et;
```

   <p>En la función show se crea el mundo, el ancho de la pantalla será de 10m.</p>    

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

   <p>Se inicializan las variables de texto.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/360);
```

   <p>Se crea un suelo usando una cadena.</p>

```javascript
Vector2[] chain=new Vector2[2];
chain[0]=new Vector2(-La/2,(-An/2)+.01f);
chain[1]=new Vector2(La/2,(-An/2)+.01f);
BodySu=world.createBody(createBody(0,0,'S'));
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
SuFix=BodySu.createFixture(createFix(cadena,0,0,0));
```

   <p>Se crea una forma circular, se la da una velocidad inicial en x.</p>

```javascript
CircleShape Crshape=new CircleShape();
BodyCr=world.createBody(createBody((-La/2)+1,(An/2)-1,'D'));
Crshape.setRadius(.5f);
CrFix=BodyCr.createFixture(createFix(Crshape,1,0,.9f));
Crshape.dispose();
BodyCr.setFixedRotation(true);
BodyCr.setLinearVelocity(.4f,0);
```

   <p>Se inicializan las variables de gráficos.</p>

```javascript
str=FragShader();
ShaderProgram.pedantic=false;
```

   <p>En la función render, limpiar la pantalla y crear el formato de texto.</p>

```javascript
Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
Gdx.gl.glClearColor(0.1f, 0f, 0.1f, 0.5f);
DecimalFormat df = new DecimalFormat("#0.00");
```

   <p>Se obtiene el valor de velocidad resultante.</p>

```javascript
velx=BodyCr.getLinearVelocity().x;
vely=BodyCr.getLinearVelocity().y;
velr=(float)Math.sqrt(Math.pow(velx,2)+Math.pow(vely,2));
```

   <p>Se guarda el valor de la masa y se usan las ecuaciones para calcular la energía cinética, potencial y total.</p>

```javascript
float masa=BodyCr.getMass();
Ec=.5f*masa*velr*velr;
Ep=masa*g*(BodyCr.getPosition().y+(An/2)-.01f-.5f);
Et=Ec+Ep;
```

   <p>La función que dibujará la gráfica necesita saber la posición en que se quiere (cx, cy), un valor de escala(s), ya que en este caso los valores serán muy grandes, más que el ancho de la pantalla y el ancho (Ann) que va a tener la grafica.</p>

```javascript
float cx=0,cy=-2,s=.03f,Ann=1.2f;
```

<h2>Se va a construir la función que permitirá graficar.</h2>
    <p>Debe recibir como parámetros, el ancho de grafica, posición, escala, valor a graficar y valores rgba. Se va a usar una malla con 6 puntos, cada punto tiene dos componentes (x, y). Ya que openGL trabaja con triángulos, se van a usar dos de ellos para formar un rectángulo. Cada triángulo necesita 3 puntos,</p>

```javascript
private void BarGraf(float An, float cx, float cy, float s, float valor, float r, float g, float b, float a) {
    Mesh graf;
    graf = new Mesh(false, 6, 6,
            new VertexAttribute(VertexAttributes.Usage.Position, 2, "a_position"));
```

   <p>Se crea el primer triángulo, usando los valores de posición y el ancho.</p>

```javascript
graf1v[0]=cx;
graf1v[1]=cy;
graf1v[2]=An+cx;
graf1v[3]=cy;
```

   <p>Se crea el segundo triángulo.</p>

```javascript
graf1v[4]=cx;
graf1v[5]=(valor)*s+cy;
graf1v[6]=An+cx;
graf1v[7]=graf1v[5];
```

   <p>Se envían los 6 puntos a la malla, y se escribe el orden en que se crearán los triángulos con los índices.</p>

```javascript
graf.setVertices(graf1v);
graf.setIndices(new short[]{0,1,2,1,2,3});
```

   <p>Se crea el shader con el color escrito en los parámetros de la función.</p>

```javascript
vtr=VertShader(r,g,b,a);
shader =new ShaderProgram(vtr,str);
```

   <p>Se dibuja la grafica.</p>

```javascript
shader.begin();
shader.setUniformMatrix("u_projTrans", camera.combined);
graf.render(shader, GL20.GL_TRIANGLES);
shader.end();
```

   <p>Por último, se elimina el shader y la malla que usamos.</p>

```javascript
shader.dispose();
    graf.dispose();
}
```

<h2>Regresando a la funcion render</h2>
    <p>Se usan las funciones para graficar.</p>

```javascript
BarGraf(Ann,cx,cy,s,Ec,1,.3f,0,1);
BarGraf(Ann,cx+Ann+.5f,cy,s,Ep,0,1,.3f,1);
BarGraf(Ann,cx+(2*(Ann+.5f)),cy,s,Ec+Ep,.3f,0,1,1);
```

   <p>Se cambia los valores a cadena de caracteres.</p>

```javascript
Ecs=df.format(Ec);
Eps=df.format(Ep);
Ets=df.format(Et);
```

   <p>Se grafica los valores debajo de las gráficas.</p>

```javascript
batch.begin();
font.draw(batch,"Ec",Box2Pix(0+.3f),Boy2Piy(-2.1f));
font.draw(batch,"Ep",Box2Pix(Ann+.5f+.3f),Boy2Piy(-2.1f));
font.draw(batch,"ET",Box2Pix(2*(Ann+.5f)+.3f),Boy2Piy(-2.1f));
font.draw(batch,Ecs,Box2Pix(0+.2f),Boy2Piy(-2.6f));
font.draw(batch,Eps,Box2Pix(Ann+.5f+.2f),Boy2Piy(-2.6f));
font.draw(batch,Ets,Box2Pix(2*(Ann+.5f)+.2f),Boy2Piy(-2.6f));
batch.end();
```

   <p>Al final de la función render usar la ecuación de tiempo.</p>

```javascript
t=t+delta;
```

   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba10_experimento_8_energia/imagen01.jpg?raw=true" width="60%"></p>
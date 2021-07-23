<h1>Prueba de gráficos</h1>
    <p>Primero se declaran las variables que se van a usar para crear el mundo, el ancho de la pantalla será equivalente a 10 metros y no habrá aceleración debido a la gravedad.</p>

```javascript
float H,W,R,An,La;
```

   <p>Se crea el mundo en la función show.</p>

```javascript
An=10;
W=Gdx.graphics.getWidth();
H=Gdx.graphics.getHeight();
R=H/An;
La=W/R;
world=new World(new Vector2(0,0),true);
renderer=new Box2DDebugRenderer();
camera=new OrthographicCamera(La,An);
```

   <p>Se crean los cuerpos que se van a usar.</p>

```javascript
private Body BodyCr,BodyCa;
private Fixture CrFix,CaFix;
```

   <p>Se crean variables para usar gráficos.</p>

```javascript
private ShaderProgram shader;
private BitmapFont font;
private SpriteBatch batch;
private Mesh mesh;
private String str,fps,tiempo,lineas;
```

   <p>Se definen los cuerpos: una esfera, y una cadena que cubra casi toda la pantalla, usando las funciones antes hechas.</p>

```javascript
CircleShape Crshape=new CircleShape();
BodyCr=world.createBody(createBody(0,-4.5f,'D'));
Crshape.setRadius(.5f);
CrFix=BodyCr.createFixture(createFix(Crshape,0,0,1));
Crshape.dispose();
Vector2[] chain=new Vector2[5];
chain[0]=new Vector2(-9,-6);
chain[1]=new Vector2(-9,4);
chain[2]=new Vector2(9,4);
chain[3]=new Vector2(9,-6);
chain[4]=new Vector2(-9,-6);
BodyCa=world.createBody(createBody(0,0,'S'));
ChainShape cadena;
cadena=new ChainShape();
cadena.createChain(chain);
CaFix=BodyCa.createFixture(createFix(cadena,0,0,1));
```

   <p>Se declaran las variables donde se van a guardar las gráficas.</p>

```javascript
private List<Float> grafica;
private List<Float> graficaf;    
```

   <p>Se inicializan las listas y variables para gráficos.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
font.getData().setScale(H/360);
grafica=new ArrayList<Float>();
graficaf=new ArrayList<Float>();
grafica.clear();
graficaf.clear();
```

   <p>Para el uso de openGL es necesario usar un shader. Para crearlo se van a usar dos funciones, las cuales sirven para que los gráficos aparezcan del tamaño adecuado según la cámara, así como para cambiar su color.</p>

```javascript
private String VertShader(float r,float g,float b,float a) {
    String R,G,B,A;
    R=Float.toString(r);
    G=Float.toString(g);
    B=Float.toString(b);
    A=Float.toString(a);
    return  "attribute vec4 a_position;    \n" +
            "attribute vec4 a_color;\n" +
            "uniform mat4 u_projTrans;\n" +
            "varying vec4 v_color;" +
            "void main()                  \n" +
            "{                            \n" +
            "   v_color = vec4("+R+", "+G+", "+B+", "+A+"); \n" +
            " gl_Position =  u_projTrans * a_position;\n" +
            "}                            \n" ;
}

private String FragShader() {
    return "varying vec4 v_color;\n" +
            "void main()                                  \n" +
            "{                                            \n" +
            "  gl_FragColor = v_color;\n" +
            "}"; 
}
```

   <p>Se inicializa el shader en función show, se recomienda usar pendantic=false</p>

```javascript
str=FragShader();
ShaderProgram.pedantic=false;
```

   <p>En la función render, se limpia la pantalla y si se quiere se puede cambiar el color del fondo de la pantalla, se crea el formato de salida de texto.</p>

```javascript
Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
Gdx.gl.glClearColor(0.1f,0f,0.1f,0.5f);
DecimalFormat df = new DecimalFormat("#0.00");
```

   <p>Hasta el momento solo debería aparecer un circulo sin movimiento.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_graficos/imagen01.jpg?raw=true" width="60%"></p>
   <p>Se crean dos variables, una para guardar el tiempo y otra para usarla como temporizador.</p>

```javascript
private float t=0,tn=0;
```

   <p>Se definen las dos variables en el final de la función render.</p>

```javascript
t=t+delta;
tn=tn-delta;
```

   <p>Se le da un valor inicial al círculo.</p>

```javascript
if(t < 2*delta){
BodyCr.setLinearVelocity(5,12);
}
```

   <p>Ahora que el cuerpo está en movimiento, graficará la trayectoria que está siguiendo.</p>
   <p>Primero en cada iteración se guardará su posición, en "x" y en "y".</p>

```javascript
grafica.add(BodyCr.getPosition().x);
grafica.add(BodyCr.getPosition().y);
```

   <p>Cuando el tiempo sea mayor a cero se empezara a graficar. La razón es para evitar dibujar solo un punto</p>

```javascript
if(t>0){
}
```

   <p>Dentro de la condicional se escribirá el código para graficar.</p>
   <p>Las coordenadas fueron guardados en una lista, así que deben pasar a un arreglo</p>

```javascript
Int tam = grafica.size();
float[] graf = new float[tam];
int i = 0;
for (Float f : grafica) {
    graf[i++] = f;
}
```

   <p>Lo siguiente es crear los índices con la siguiente serie 0,1,1,2,2,3,...,n-1,n cada par de números representan una linea.</p>

```javascript
i=0;
short[] indi = new short[(tam - 2)];
for (int k=0;k < (tam-2)/2;k++) {
    i=2*k;
    indi[i] =(short)k;
    indi[i+1]=(short)(k+1);
}
```

   <p>Se inicializa la malla con el número máximo de vértices, máximo número de índices, y la cantidad de datos que posee cada punto, en este caso es de 2, para "x" y "y".</p>

```javascript
mesh = new Mesh(true, tam / 2, tam-2,
new VertexAttribute(VertexAttributes.Usage.Position, 2, "a_position"));
```

   <p>Después se envían los vértices y los índices.</p>

```javascript
mesh.setVertices(graf);
mesh.setIndices(indi);
```

   <p>Se le da un color y se inicializa el shader.</p>

```javascript
String vtr=VertShader(1,1,1,0);
shader =new ShaderProgram(vtr,str);
```

   <p>Para graficar se usa un begin-end. Cada vez que se mande a graficar se debe pasar la matriz de proyección de la cámara. Se usa el modo de graficar líneas con openGL 2.0.</p>

```javascript
shader.begin();
shader.setUniformMatrix("u_projTrans", camera.combined);
mesh.render(shader, GL20.GL_LINES);
shader.end();
```

   <p>Después de graficar, borrar la malla y el shader.</p>

```javascript
mesh.dispose();
shader.dispose();
```

   <p>Ya se podrá ver que la trayectoria se está dibujando.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_graficos/imagen02.jpg?raw=true" width="60%"></p>
   <p>Se va a crear una función para poder graficar desde una lista, se podrá modificar la posición, escala y el color de la gráfica. Esta función solo va a usarse si hay al menos 4 datos, es decir dos puntos, o bien una línea.</p>

```javascript
private void plot(List<Float> Gra, float px, float py, float sx, float sy, float roj, float gre, float blu, float alp,boolean bol) {
if(Gra.size()>=4) {
    
}
}
```

   <p>Se van a pasar los puntos a un arreglo, pero a diferencia de la anterior función, se debe modificar el valor de "x" y "y" de manera independiente, así que se usa una variable auxiliar para indicar cual valor se debe modificar.</p>

```javascript
int Tam = Gra.size();
float[] vgra = new float[Tam];
int k = 0;
for (Float f : Gra) {
    if(bol){
        vgra[k++]=(f*sx)+px;
    }else {
        vgra[k++]=(f*sy)+py;
    }
    bol=!bol;
}
```

   <p>Se crean los índices.</p>

```javascript
int i=0;
short[] indic = new short[(Tam - 2)];
for (k=0;k < (Tam-2)/2;k++) {
    i=2*k;
    indic[i] =(short)k;
    indic[i+1]=(short)(k+1);
}
```

   <p>Se crea la malla y el shader, y se manda a dibujar. Nuevamente se elimina la malla y el shader al final.</p>

```javascript
Mesh grafp;
grafp = new Mesh(true, Tam / 2, Tam - 2,
        new VertexAttribute(VertexAttributes.Usage.Position, 2, "a_position"));
grafp.setVertices(vgra);
grafp.setIndices(indic);
String vtr = VertShader(roj, gre, blu, alp);
shader = new ShaderProgram(vtr, str);

shader.begin();
shader.setUniformMatrix("u_projTrans", camera.combined);
grafp.render(shader, GL20.GL_LINES);
shader.end();
grafp.dispose();
shader.dispose();
Se añaden las coordenadas a una nueva grafica y se usa la función antes creada
graficaf.add(BodyCr.getPosition().x);
graficaf.add(BodyCr.getPosition().y);
plot(graficaf,3,3,.2f,.2f,.7f,.5f,.4f,1,true);
```

   <p>Ahora se podrá ver una réplica de la trayectoria más pequeña y de un color diferente.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_graficos/imagen03.jpg?raw=true" width="60%"></p>
   <p>Se usará el temporizador en un condicional para que cada cierto tiempo se guarden los datos de FPS, el tiempo y las líneas de una de las gráficas. También se usará para reiniciar el temporizador y la velocidad del cuerpo.</p>

```javascript
if(tn <= 0)
{
    fps=df.format(1/delta);
tiempo=df.format(t);
lineas=df.format((grafica.size()/2)-1);
tn=.1f;
vel=13/Math.sqrt(Math.pow(BodyCr.getLinearVelocity().x,2)+Math.pow(BodyCr.getLinearVelocity().y,2));
BodyCr.setLinearVelocity((float)(BodyCr.getLinearVelocity().x*vel),
(float)(BodyCr.getLinearVelocity().y*vel));
 }
```

   <p>Por último se dibujará los datos antes guardados.</p>
```javascript
batch.begin();
font.draw(batch,"FPS = "+fps,Box2Pix(-6),Boy2Piy(4.5f));
font.draw(batch,"Tiempo = "+tiempo,Box2Pix(-2),Boy2Piy(4.5f));
font.draw(batch,"Indices = "+lineas,Box2Pix(2),Boy2Piy(4.5f));
batch.end();
```

   <p>Así se podrá ver al final.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_graficos/imagen04.jpg?raw=true" width="60%"></p>
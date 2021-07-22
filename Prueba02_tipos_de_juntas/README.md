<h1>Segunda Prueba: Juntas</h1>
    <p>En esta prueba se usará una forma de mantener el tamaño de los objetos, es decir que no se vean pequeños o más grandes al hacer funcionar la aplicación en otros dispositivos.</p>
    <p>Para hacer esto se dará como valor al ancho de la pantalla 48 metros. Se devidirá los pixeles del ancho de la pantalla entre 48 para saber cuántos pixeles equivalen a un metro. Se usará este valor para dividir el largo de la pantalla.</p>
    <p>Se declaran variables:</p>

```javascript
private float W,H,R,An,La;
```

    <p>En la función show se crea la pantalla</p>

```javascript
An=48;
W=Gdx.graphics.getWidth();
H=Gdx.graphics.getHeight();
R=H/An;
La=W/R;

world=new World(new Vector2(0,-10),true);
renderer=new Box2DDebugRenderer();
camera=new OrthographicCamera(La,An);
```

    <p>Se crearán funciones para poder crear Body y Fixture de una manera más eficiente. Para crear el Body se usarán como parámetros la posición y el tipo de cuerpo, para la Fixture se usarán la forma y propiedades. El tipo de cuerpo por será estático.</p>
<h2>Crear Fixture</h2>

```javascript
private FixtureDef createFix(Shape cushape, float Den, float Fri, float Res) {
    FixtureDef def=new FixtureDef();
    def.shape=cushape;
    def.density=Den;
    def.friction=Fri;
    def.restitution=Res;
    return def;
}
```

<h2>Crear Body</h2>

```javascript
private BodyDef createBody(float Px, float Py, Character Tipo) {
    BodyDef def=new BodyDef();
    def.position.set(Px,Py);
    switch(Tipo)
    {
        case 'D':
            def.type= BodyDef.BodyType.DynamicBody;
            break;
        case 'S':
            def.type= BodyDef.BodyType.StaticBody;
            break;
        case 'K':
            def.type= BodyDef.BodyType.KinematicBody;
            break;
        default:
            def.type= BodyDef.BodyType.StaticBody;
    }
    return def;
}
```

    <p>Se usarán dos formas</p>

```javascript
CircleShape Crshape=new CircleShape();
PolygonShape Cushape=new PolygonShape();
```

<h2>Junta de revolución</h2>
    <p>Se declaran los cuerpos.</p>

```javascript
private Body BodyTr1;
private Fixture Tr1Fix;
private Body BodyCr1,BodyCr2;
private Fixture Cr1Fix,Cr2Fix;
```

    <p>Se definen en la función show</p>

```javascript
BodyCr1=world.createBody(createBody(-20,15,'D'));
Crshape.setRadius(2);
CrFix=BodyCr1.createFixture(createFix(Crshape,1,0,1));

BodyCr2=world.createBody(createBody(-20,15,'D'));
Crshape.setRadius(1);
Cr2Fix=BodyCr2.createFixture(createFix(Crshape,1,0,1));

Vector2[] vertices=new Vector2[3];
vertices[0]=new Vector2(-1,-1);
vertices[1]=new Vector2(0,1);
vertices[2]=new Vector2(1,-1);
BodyTr1=world.createBody(createBody(-20,15,'S'));
Cushape.set(vertices);
Tr1Fix=BodyTr1.createFixture(createFix(Cushape,.1f,.1f,.1f));
```

    <p>Se declaran dos juntas de revolución.</p>

```javascript
private RevoluteJoint JoinRev1,JoinRev2;
```

    <p>Se hace una función para crear la junta de revolución.</p>

```javascript
private JointDef createRevjoin(Body BA, Body BB, float Ax, float Ay, float Bx, float By, boolean cl, boolean M, float Mt, float s) {
    RevoluteJointDef Revjoin=new RevoluteJointDef();
    Revjoin.bodyA=BA;
    Revjoin.bodyB=BB;
    Revjoin.localAnchorA.set(Ax,Ay);
    Revjoin.localAnchorB.set(Bx,By);
    Revjoin.collideConnected=cl;
    Revjoin.enableMotor=M;
    Revjoin.maxMotorTorque=Mt;
    Revjoin.motorSpeed=s;
    return Revjoin;
}
```

    <p>Se crean las dos juntas de revolución en la función show.</p>

```javascript
JoinRev1=(RevoluteJoint) world.createJoint(createRevjoin(BodyTr1,BodyCr1,0,0,-5,0,true,false,0,0));
JoinRev2=(RevoluteJoint) world.createJoint(createRevjoin(BodyCr1,BodyCr2,0,0,-5,0,true,false,0,0));
```

    <p>*los Body son creados en el mismo lugar para que la asignación de LocalAnchor sea más facil.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen01.jpg?raw=true" width="20%"></p>

<h2>Junta prismática</h2>
    <p>Se declaran los cuerpos.</p>

```javascript
private Body BodyCu1,BodyCu2;
private Fixture Cu1Fix,Cu2Fix;
```

    <p>Se definen en la función show.</p>

```javascript
BodyCu1=world.createBody(createBody(-20,0,'S'));
Cushape.setAsBox(.5f,.5f);
Cu1Fix=BodyCu1.createFixture(createFix(Cushape,1,0,1));

BodyCu2=world.createBody(createBody(-20,0,'D'));
Cushape.setAsBox(.5f,.5f);
Cu2Fix=BodyCu2.createFixture(createFix(Cushape,1,0,1));
```

    <p>Se declara la junta prismática</p>

```javascript
private PrismaticJoint JoinPr;
```

    <p>Se hace una función para crear la junta prismática.</p>

```javascript
private JointDef createPrJoin(Body BA, Body BB, float Ax, float Ay, float Bx, float By, boolean cl, boolean lm, boolean M,float s, float L, float U, int lx, int ly) {
    PrismaticJointDef Prjoin=new PrismaticJointDef();
    Prjoin.bodyA=BA;
    Prjoin.bodyB=BB;
    Prjoin.localAnchorA.set(Ax,Ay);
    Prjoin.localAnchorB.set(Bx,By);
    Prjoin.collideConnected=cl;
    Prjoin.enableLimit=lm;
    Prjoin.enableMotor=M;
    Prjoin.motorSpeed=s;
    Prjoin.lowerTranslation=L;
    Prjoin.upperTranslation=U;
    Prjoin.localAxisA.set(lx,ly);
    return Prjoin;
}
```

    <p>Se crea la junta prismática en la funcion show.</p>

```javascript
JoinPr=(PrismaticJoint) world.createJoint(createPrJoin(BodyCu1,BodyCu2,.5f,0,-.5f,0,true,true,false,0,0,10,1,-1));
```

<p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen02.jpg?raw=true" width="20%"></p>

<h2>Junta soldadura</h2>
    <p>Se declaran los cuerpos.</p>

```javascript
private Body BodyCr3,BodyCr4;
private Fixture Cr3Fix,Cr4Fix;
private Body BodyCu3;
private Fixture Cu3Fix,Cu4Fix;
```

    <p>Se definen los cuerpos en funcion show.</p>

```javascript
BodyCu3=world.createBody(createBody(-20,-15,'D'));
Cushape.setAsBox(1.5f,1.5f);
Cu3Fix=BodyCu3.createFixture(createFix(Cushape,1,0,1));

BodyCr3=world.createBody(createBody(-20,-15,'D'));
Crshape.setRadius(1);
Cr3Fix=BodyCr3.createFixture(createFix(Crshape,1,1,0));

BodyCr4=world.createBody(createBody(-20,-15,'D'));
Crshape.setRadius(1);
Cr4Fix=BodyCr4.createFixture(createFix(Crshape,1,1,0));
```

    <p>Se declara la junta soldadura.</p>

```javascript
private WeldJoint JoinWel1,JoinWel2;
```

    <p>Se hace una función para crear la junta de soldadura.</p>

```javascript
private JointDef createWelJoin(Body BA, Body BB, float Ax, float Ay, float Bx, float By, boolean coll, float Dm, int Fr) {
    WeldJointDef Weljoin=new WeldJointDef();
    Weljoin.bodyA=BA;
    Weljoin.bodyB=BB;
    Weljoin.collideConnected=coll;
    Weljoin.localAnchorA.set(Ax,Ay);
    Weljoin.localAnchorB.set(Bx,By);
    Weljoin.dampingRatio=Dm;
    Weljoin.frequencyHz=Fr;
    return Weljoin;
}
```

    <p>Se crean las juntas de soldadura en la función show.</p>

```javascript
JoinWel1=(WeldJoint) world.createJoint(createWelJoin(BodyCu3,BodyCr3,-1.5f,-2,.3f,1.3f,true,0,0));
JoinWel2=(WeldJoint) world.createJoint(createWelJoin(BodyCu3,BodyCr4,1.5f,-2,-.3f,1.3f,true,0,0));
```

    <p>Haciendo esto aparece un carrito, pero se cae al vacio, así que se agregará un suelo.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen03.jpg?raw=true" width="20%"></p>
    <p>Se declara el cuerpo.</p>

```javascript
private Body BodyCu4;
```

    <p>Se define en la función show.</p>

```javascript
BodyCu4=world.createBody(createBody(0,-20,'S'));
Cushape.setAsBox(25,.5f);
Cu4Fix=BodyCu4.createFixture(createFix(Cushape,1,.1f,.1f));
```

    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen04.jpg?raw=true" width="20%"></p>
<h2>Junta de distancia</h2>
    <p>Se declaran los cuerpos.</p>

```javascript
private Body BodyCr5,BodyCr6;
private Fixture Cr5Fix,Cr6Fix;
private Body BodyTr2;
private Fixture Tr2Fix;
```

    <p>Se definen los cuerpos en la función show.</p>

```javascript
BodyTr2=world.createBody(createBody(0,15,'S'));
Cushape.set(vertices);
Tr2Fix=BodyTr2.createFixture(createFix(Cushape,1,0,1));

BodyCr5=world.createBody(createBody(10,15,'D'));
Crshape.setRadius(2);
Cr5Fix=BodyCr5.createFixture(createFix(Crshape,1,1,0));

BodyCr6=world.createBody(createBody(20,15,'D'));
Crshape.setRadius(1);
Cr6Fix=BodyCr6.createFixture(createFix(Crshape,1,1,0));
```

    <p>Se declara la junta.</p>

```javascript
private DistanceJoint JoinDis1,JoinDis2;
```

    <p>Se hace una función para crear la junta de distancia.</p>

```javascript
private JointDef createDisjoin(Body BA,Body BB,float Ax,float Ay,float Bx,float By,boolean cl,float Le,float Dm,float Fr) {
    DistanceJointDef Disjoin=new DistanceJointDef();
    Disjoin.bodyA=BA;
    Disjoin.bodyB=BB;
    Disjoin.localAnchorA.set(Ax,Ay);
    Disjoin.localAnchorB.set(Bx,By);
    Disjoin.collideConnected=cl;
    Disjoin.length=Le;
    Disjoin.dampingRatio=Dm;
    Disjoin.frequencyHz=Fr;
    return Disjoin;
}
```

    <p>Se hacen las juntas de distancia.</p>

```javascript
JoinDis1=(DistanceJoint) world.createJoint(createDisjoin(BodyTr2,BodyCr5,0,0,0,0,true,10,0,0));
JoinDis1=(DistanceJoint) world.createJoint(createDisjoin(BodyCr5,BodyCr6,0,0,0,0,true,10,0,0));
```

    <p>En esta ocasión se asignó las posiciones correctas desde la creación del cuerpo, así que en los LocalAnchor se dejaron en 0 para ambas juntas. Si se cambia la longitud de distancia al iniciar la simulación las posiciones comenzarán a corregirse.</p>
    <p>*no asignar una distancia de 0, ocasionará problemas al simular.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen05.jpg?raw=true" width="20%"></p>
<h2>Junta de polea</h2>
    <p>Se declaran los cuerpos.</p>

```javascript
private Body BodyCu5,BodyCu6;
private Fixture Cu5Fix,Cu6Fix;
```

    <p>Se definen los cuerpos en función show.</p>

```javascript
BodyCu5=world.createBody(createBody(10,-5,'D'));
Cushape.setAsBox(.5f,.5f);
Cu5Fix=BodyCu5.createFixture(createFix(Cushape,1,0,.1f));

BodyCu6=world.createBody(createBody(15,-5,'D'));
Cushape.setAsBox(.5f,.5f);
Cu6Fix=BodyCu6.createFixture(createFix(Cushape,1,0,.1f));
```

    <p>Se declara la junta.</p>

```javascript
private PulleyJoint JoinPul;
```

    <p>Se hace una función para crear la junta.</p>

```javascript
private JointDef createPuljoin(Body BA,Body BB,float Ax,float Ay,float Bx,float By,float Gax,float Gay,float Gbx,float Gby,boolean cl,float La,float Lb,float r) {
    PulleyJointDef Puljoin=new PulleyJointDef();
    Puljoin.bodyA=BA;
    Puljoin.bodyB=BB;
    Puljoin.localAnchorA.set(Ax,Ay);
    Puljoin.localAnchorB.set(Bx,By);
    Puljoin.groundAnchorA.set(Gax,Gay);
    Puljoin.groundAnchorB.set(Gbx,Gby);
    Puljoin.collideConnected=true;
    Puljoin.lengthA=La;
    Puljoin.lengthB=Lb;
    Puljoin.ratio=r;
    return Puljoin;
}
```

    <p>Se hace la junta de polea.</p>

```javascript
JoinPul=(PulleyJoint) world.createJoint(createPuljoin(BodyCu5,BodyCu6,0,.5f,0,.5f,10,5,15,5,true,5,5,1));
```

    <p>En esta junta también se le asigno la posición inicial desde el principio para que al comenzar la simulación no se movieran.</p>
    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen06.jpg?raw=true" width="20%"></p>
    <p>*las posiciones de groundAnchor son desde el centro del mundo.</p>
<h2>Junta de mouse</h2>
    <p>Se declaran los cuerpos.</p>

```javascript
private Body BodyCc,BodyGr;
private Fixture CcFix;
```

    <p>Se definen en la función show.</p>

```javascript
BodyCc=world.createBody(createBody(0,0,'D'));
Cushape.setAsBox(1,1);
CcFix=BodyCc.createFixture(createFix(Cushape,1,1,0));

BodyGr=world.createBody(createBody(0,0,'S'));
```

    <p>Ya que se crearon los últimos cuerpos se eliminan las formas.</p>

```javascript
Cushape.dispose();
Crshape.dispose();
```

    <p>Se declara la junta.</p>

```javascript
private MouseJoint JoinMou;
```

    <p>Se hace una función para crear la junta de mouse</p>

```javascript
private JointDef createMoujoin(Body BA,Body BB,boolean cl,float m,float Dm,float Fr) {
    MouseJointDef Moujoin=new MouseJointDef();
    Moujoin.bodyA=BA;
    Moujoin.bodyB=BB;
    Moujoin.collideConnected=cl;
    Moujoin.maxForce=m*BB.getMass();
    Moujoin.dampingRatio=Dm;
    Moujoin.frequencyHz=Fr;
    return Moujoin;
}
```

    <p>Se hace la junta de mouse</p>

```javascript
JoinMou=(MouseJoint) world.createJoint(createMoujoin(BodyGr,BodyCc,true,1000,0,100));
```

    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen07.jpg?raw=true" width="20%"></p>
    <p>El cubo no se mueve ya que el target por defecto es el (0, 0).</p>
    <p>*no asignar un valor de frecuencia pequeño ya que e cubo recibirá menor fuerza, si se asigna un cero dará error al crear la junta.</p>
    <p>Se hacen funciones para el cambio de coordenadas de pixel a metros. El (0, 0) en metros es el centro de la pantalla.</p>

```javascript
private float Pix2Boy(float Py) {
    return (An/2)-(Py/R);
}

private float Pix2Box(float  Px) {
    return (Px/R)-(La/2);
}
```

    <p>Se asigna el target con la posición del mouse en la pantalla.</p>

```javascript
JoinMou.setTarget(new Vector2(Pix2Box(Gdx.input.getX()),Pix2Boy(Gdx.input.getY())));
```

    <p>Ahora el cubo se podrá mover, pero en el caso en detecte valores fuera de rango de la pantalla el cubo se seguirá moviendo. Así que se hará una comprobación de la posición del mouse antes de asignar el target. Se agregarán variables para guardar la posición del mouse.</p>

```javascript
private  float corx,cory;
```

    <p>Se realiza la comprobación, si está en el rango se le asigna el valor de la junta.</p>

```javascript
corx=Gdx.input.getX();
cory=Gdx.input.getY();

if(corx<=0||corx>=W||cory<=0||cory>=H)
{
    JoinMou.setTarget(new Vector2(0,0));
}else{
    JoinMou.setTarget(new Vector2(Pix2Box(corx),Pix2Boy(cory)));
}
```

    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen08.jpg?raw=true" width="20%"></p>
    <p>Para eliminar de forma segura los cuerpos y juntas se usará el siguiente código en la función dispose, justo después se elimina el renderer y al último el mundo.</p>
<h2>Eliminar todas las juntas y cuerpos</h2>
    <p>Eliminar Fixture una por una</p>

```javascript
BodyCr1.destroyFixture(Cr1Fix);
BodyCr2.destroyFixture(Cr2Fix);
BodyTr1.destroyFixture(Tr1Fix);
BodyCu1.destroyFixture(Cu1Fix);
BodyCu2.destroyFixture(Cu2Fix);
BodyCr3.destroyFixture(Cr3Fix);
BodyCr4.destroyFixture(Cr4Fix);
BodyCu3.destroyFixture(Cu3Fix);
BodyCu4.destroyFixture(Cu4Fix);
BodyTr2.destroyFixture(Tr2Fix);
BodyCr5.destroyFixture(Cr5Fix);
BodyCr6.destroyFixture(Cr6Fix);
```

    <p>Eliminar las juntas</p>

```javascript
Array<Joint> joints=new Array<Joint>();
    world.getJoints(joints);
    for(int i = 0; i < joints.size; i++)
    {
        world.destroyJoint(joints.get(i));
    }    
```

    <p>Eliminar los cuerpos</p>

```javascript
Array<Body> bodies = new Array<Body>();
    world.getBodies(bodies);
    for(int i = 0; i < bodies.size; i++)
    {
        world.destroyBody(bodies.get(i));
    }
    
    renderer.dispose();
    world.dispose();    
```

    <p>*es posible no eliminar las juntas, ya que se eliminan cuando al menos uno de los cuerpos de los que dependen es destruido.</p>
    <p>Como extra se agregará una comprobación de la posición de los cuerpos del carrito, para que cuando se caiga, aparezca justo arriba de la pantalla.</p>

```javascript
if(BodyCr3.getPosition().y<((-An/2)-1.5f)&&BodyCr4.getPosition().y<((-An/2)-1.5f)&&BodyCu3.getPosition().y<((-An/2)-1.5f))
{
    BodyCr3.setTransform(BodyCr3.getPosition().x-BodyCu3.getPosition().x,BodyCr3.getPosition().y+An+5,BodyCr3.getAngle());
    BodyCr4.setTransform(BodyCr4.getPosition().x-BodyCu3.getPosition().x,BodyCr4.getPosition().y+An+5,BodyCr4.getAngle());
    BodyCu3.setTransform(0,BodyCu3.getPosition().y+An+5,BodyCu3.getAngle());
}
```

    <p>Al usar la transformación el carrito aparecerá con la misma velocidad con la que estaba cayendo así que se modificarán los valores de velocidad a cero justo después de aplicarse la transformación.</p>

```javascript
BodyCr3.setLinearVelocity(0,0);
BodyCr4.setLinearVelocity(0,0);
BodyCu3.setLinearVelocity(0,0);
```
<h2>Texto en pantalla</h2>
    <p>Ahora se agregará texto encima del cubo, el cual mostrará los componentes de fuerza y la magnitud. Primero se declaran las variables donde se guarda los componentes de fuerza y una cadena de caracteres.</p>

```javascript
private float Fx,Fy;
private String str;
```

    <p>Se usarán funciones para el cambio de coordenadas de metros a pixeles.</p>

```javascript
private float Boy2Piy(float Cy) {
    return An*R-((An/2)-Cy)*R;
}

private float Box2Pix(float Cx) {
    return (Cx+ (W / 2)) * R;
}
```

    <p>Ahora se definen variables de batch (para dibujar en pantalla) y font (para traer los caracteres).</p>

```javascript
private BitmapFont font;
private SpriteBatch batch;
```

    <p>Se inicializan en la función show.</p>

```javascript
font=new BitmapFont();
batch=new SpriteBatch();
```

    <p>Se crea un nuevo formato para mostrar hasta un decimal en la funcion render.</p>

```javascript
DecimalFormat df = new DecimalFormat("#0.00");
```

    <p>Se guardan los componentes de fuerza.</p>

```javascript
Fx=JoinMou.getReactionForce(1/delta).x;
Fy=JoinMou.getReactionForce(1/delta).y;
```

    <p>Cada texro se imprimirá al lado derecho del cubo con una altura de un metro y medio de diferencia.</p>

```javascript
batch.begin();
str=df.format(Math.sqrt(Math.pow(Fx,2)+Math.pow(Fy,2)));
font.draw(batch,"magnitud: "+str,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y + 1.5f));
str=df.format(Fx);
font.draw(batch,"X: "+str,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y));
str=df.format(Fy);
font.draw(batch,"Y: "+str,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y - 1.5f))   ;
batch.end();
```

    <p>Los números cambian a 60 veces por segundo así que no se puede notar cual número se está mostrando, por eso se agregarán nuevas variables para guardar el texto que se va a mostrar, sin embargo el texto va a actualizase cada 12 imágenes.</p>
    <p>Se cambia la variable de texto por tres y se agrega un contador.</p>

```javascript
private String str1,str2,str3;
private int cont;
```

    <p>Se inicializa el contador en la función show.</p>

```javascript
cont=12;
```

    <p>Se modifica el código de obtención de los componentes de fuerza.</p>

```javascript
if(cont==0) {
    Fx = JoinMou.getReactionForce(1 / delta).x;
    Fy = JoinMou.getReactionForce(1 / delta).y;
    str1=df.format(Math.sqrt(Math.pow(Fx,2)+Math.pow(Fy,2)));
    str2=df.format(Fx);
    str3=df.format(Fy);
    cont=12;
}cont=cont-1;
```

    <p>Se debe seguir dibujando el texto a un lado del cubo.</p>

```javascript
batch.begin();
font.draw(batch,"magnitud: "+str1,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y + 1.5f));
font.draw(batch,"X: "+str2,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y));
font.draw(batch,"Y: "+str3,Box2Pix(BodyCc.getPosition().x + 1.5f),Boy2Piy(BodyCc.getPosition().y - 1.5f))   ;
batch.end();
```

    <p>Al pasar la aplicación al celular el texto podría verse más grande o más pequeño, así que se debe agregar un factor de escala en la función show.</p>

```javascript
font.getData().setScale(H/480);
```

    <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba02_tipos_de_juntas/imagen08.jpg?raw=true" width="20%"></p>
    <p>No olvidar eliminar el font y batch en la función dispose, no importa donde se agregen las lineas</p>

```javascript
font.dispose();
batch.dispose();
```
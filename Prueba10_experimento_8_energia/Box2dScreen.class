package com.mygdx.prueba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.text.DecimalFormat;

public class Box2dScreen extends BaseScreen {

    public Box2dScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

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

    @Override
    public void show() {

        An=10;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,-g),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/360);

        Vector2[] chain=new Vector2[2];
        chain[0]=new Vector2(-La/2,(-An/2)+.01f);
        chain[1]=new Vector2(La/2,(-An/2)+.01f);

        BodySu=world.createBody(createBody(0,0,'S'));
        ChainShape cadena;
        cadena=new ChainShape();
        cadena.createChain(chain);
        SuFix=BodySu.createFixture(createFix(cadena,0,0,0));

        CircleShape Crshape=new CircleShape();
        BodyCr=world.createBody(createBody((-La/2)+1,(An/2)-1,'D'));
        Crshape.setRadius(.5f);
        CrFix=BodyCr.createFixture(createFix(Crshape,1,0,.9f));
        Crshape.dispose();
        BodyCr.setFixedRotation(true);
        BodyCr.setLinearVelocity(.4f,0);

        str=FragShader();
        ShaderProgram.pedantic=false;
    }

    private FixtureDef createFix(Shape cushape, float Den, float Fri, float Res) {
        FixtureDef def=new FixtureDef();
        def.shape=cushape;
        def.density=Den;
        def.friction=Fri;
        def.restitution=Res;
        return def;
    }

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

    @Override
    public void dispose() {

        Gdx.input.vibrate(500);

        Array<Joint> joints=new Array<Joint>();
        world.getJoints(joints);
        for(int u = 0; u < joints.size; u++)
        {
            world.destroyJoint(joints.get(u));
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(int u = 0; u < bodies.size; u++)
        {
            world.destroyBody(bodies.get(u));
        }

        renderer.dispose();
        world.dispose();
        font.dispose();
        batch.dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0f, 0.1f, 0.5f);
        DecimalFormat df = new DecimalFormat("#0.00");

        velx=BodyCr.getLinearVelocity().x;
        vely=BodyCr.getLinearVelocity().y;
        velr=(float)Math.sqrt(Math.pow(velx,2)+Math.pow(vely,2));

        float masa=BodyCr.getMass();
        Ec=.5f*masa*velr*velr;
        Ep=masa*g*(BodyCr.getPosition().y+(An/2)-.01f-.5f);
        Et=Ec+Ep;

        float cx=0,cy=-2,s=.03f,Ann=1.2f;
        BarGraf(Ann,cx,cy,s,Ec,1,.3f,0,1);
        BarGraf(Ann,cx+Ann+.5f,cy,s,Ep,0,1,.3f,1);
        BarGraf(Ann,cx+(2*(Ann+.5f)),cy,s,Ec+Ep,.3f,0,1,1);

        Ecs=df.format(Ec);
        Eps=df.format(Ep);
        Ets=df.format(Et);

        batch.begin();
        font.draw(batch,"Ec",Box2Pix(0+.3f),Boy2Piy(-2.1f));
        font.draw(batch,"Ep",Box2Pix(Ann+.5f+.3f),Boy2Piy(-2.1f));
        font.draw(batch,"ET",Box2Pix(2*(Ann+.5f)+.3f),Boy2Piy(-2.1f));
        font.draw(batch,Ecs,Box2Pix(0+.2f),Boy2Piy(-2.6f));
        font.draw(batch,Eps,Box2Pix(Ann+.5f+.2f),Boy2Piy(-2.6f));
        font.draw(batch,Ets,Box2Pix(2*(Ann+.5f)+.2f),Boy2Piy(-2.6f));
        batch.end();

        world.step(delta, 6, 2);
        camera.update();
        renderer.render(world, camera.combined);

        t=t+delta;
    }

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

    private void BarGraf(float An, float cx, float cy, float s, float valor, float r, float g, float b, float a) {
        Mesh graf;
        graf = new Mesh(false, 6, 6,
                new VertexAttribute(VertexAttributes.Usage.Position, 2, "a_position"));
        float[] graf1v=new float[8];
        graf1v[0]=cx;
        graf1v[1]=cy;
        graf1v[2]=An+cx;
        graf1v[3]=cy;

        graf1v[4]=cx;
        graf1v[5]=(valor)*s+cy;
        graf1v[6]=An+cx;
        graf1v[7]=graf1v[5];

        graf.setVertices(graf1v);
        graf.setIndices(new short[]{0,1,2,1,2,3});

        vtr=VertShader(r,g,b,a);
        shader =new ShaderProgram(vtr,str);

        shader.begin();
        shader.setUniformMatrix("u_projTrans", camera.combined);
        graf.render(shader, GL20.GL_TRIANGLES);
        shader.end();
        shader.dispose();
        graf.dispose();
    }

    private float Boy2Piy(float Cy) { return An*R-((An/2)-Cy)*R; }
    private float Box2Pix(float Cx) { return (Cx+(La/2))*R; }
    private float Piy2Boy(float Py) { return (An/2)-(Py/R); }
    private float Pix2Box(float  Px) { return (Px/R)-(La/2); }
}
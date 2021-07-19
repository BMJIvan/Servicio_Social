package com.mygdx.prueba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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

    float H,W,R,An,La;

    private Body BodyCu,BodySu;
    private Fixture CuFix,SuFix;

    private BitmapFont font;
    private SpriteBatch batch;

    private String Fuerza,Velocidad;
    private float F=0,V=0;

    @Override
    public void show() {

        An=5;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/360);

        Vector2[] chain=new Vector2[2];
        chain[0]=new Vector2(-La/2,-.5f);
        chain[1]=new Vector2(La/2,-.5f);

        BodySu=world.createBody(createBody(0,0,'S'));
        ChainShape cadena;
        cadena=new ChainShape();
        cadena.createChain(chain);
        SuFix=BodySu.createFixture(createFix(cadena,0,1,0));

        PolygonShape Cushape=new PolygonShape();
        BodyCu=world.createBody(createBody(-2,0,'D'));
        Cushape.setAsBox(.5f,.5f);
        CuFix=BodyCu.createFixture(createFix(Cushape,1,(float)Math.pow(.6f,2),0));
        Cushape.dispose();
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
        Gdx.gl.glClearColor(0.1f,0f,0.1f,0.5f);

        BodyCu.applyForceToCenter(F,0,true);
        V=BodyCu.getLinearVelocity().x;

        DecimalFormat df = new DecimalFormat("#0.00");
        Fuerza=df.format(F);
        Velocidad=df.format(V);

        batch.begin();
        font.draw(batch,"Fuerza = "+Fuerza,Box2Pix(1),Boy2Piy(1.5f));
        font.draw(batch,"Velocidad = "+Velocidad,Box2Pix(-2),Boy2Piy(1.5f));
        batch.end();

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);

        if(V<.0001)
        {
            F=F+.01f;
        }
    }

    private float Boy2Piy(float Cy) { return An*R-((An/2)-Cy)*R; }
    private float Box2Pix(float Cx) { return (Cx+(La/2))*R; }
    private float Piy2Boy(float Py) { return (An/2)-(Py/R); }
    private float Pix2Box(float  Px) { return (Px/R)-(La/2); }
}
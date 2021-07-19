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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Array;
import java.text.DecimalFormat;

public class Box2dScreen extends BaseScreen {

    public Box2dScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private RevoluteJoint JoinRev1,JoinRev2,JoinRev3;
    private PrismaticJoint JoinPr;
    private Body BodyTr,BodyCr,BodyCu1,BodyCu2;
    private Fixture TrFix,CrFix,Cu1Fix,Cu2Fix;

    private BitmapFont font;
    private SpriteBatch batch;

    float H,W,R,An,La;

    @Override
    public void show() {

        An=1;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);

        float pox=-.45f;
        float poy=0;
        Con=false;
        on=false;

        Filter fil=new Filter();
        fil.groupIndex=-1;

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/480);

        CircleShape Crshape=new CircleShape();
        PolygonShape Cushape=new PolygonShape();
        Vector2[] vertices=new Vector2[3];
        vertices[0]=new Vector2(-.05f,-.05f);
        vertices[1]=new Vector2(0,.05f);
        vertices[2]=new Vector2(.05f,-.05f);

        BodyCr=world.createBody(createBody(pox,poy,'D'));
        Crshape.setRadius(.2f);
        CrFix=BodyCr.createFixture(createFix(Crshape,1,0,.3f));

        BodyTr=world.createBody(createBody(pox,poy,'S'));
        Cushape.set(vertices);
        TrFix=BodyTr.createFixture(createFix(Cushape,1,0,.1f));

        BodyCu1=world.createBody(createBody(pox,poy,'D'));
        Cushape.setAsBox(.4f,.0125f);
        Cu1Fix=BodyCu1.createFixture(createFix(Cushape,1,0,1));

        BodyCu2=world.createBody(createBody(pox,poy,'D'));
        Cushape.setAsBox(.075f,.05f);
        Cu2Fix=BodyCu2.createFixture(createFix(Cushape,1,0,.1f));

        Cushape.dispose();
        Crshape.dispose();

        TrFix.setFilterData(fil);
        CrFix.setFilterData(fil);
        Cu1Fix.setFilterData(fil);
        Cu2Fix.setFilterData(fil);

        BodyCu2.setLinearDamping(.5f);
        BodyCu1.setAngularDamping(.5f);
        BodyCr.setAngularDamping(.5f);

        JoinPr=(PrismaticJoint) world.createJoint(createPrJoin(BodyTr,BodyCu2,0,0,-.15f,0,false,true,false,0,0,1,1,0));
        JoinRev1=(RevoluteJoint) world.createJoint(createRevjoin(BodyTr,BodyCr,0,0,0,0,false,false,0,0));
        JoinRev2=(RevoluteJoint) world.createJoint(createRevjoin(BodyCr,BodyCu1,-.18f,0,-.38f,0,false,false,0,0));
        JoinRev3=(RevoluteJoint) world.createJoint(createRevjoin(BodyCu1,BodyCu2,.38f,0,-.07f,0,false,false,0,0));

    }

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

        Array<Joint> joints=new Array<Joint>();
        world.getJoints(joints);
        for(int i = 0; i < joints.size; i++)
        {
            world.destroyJoint(joints.get(i));
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(int i = 0; i < bodies.size; i++)
        {
            world.destroyBody(bodies.get(i));
        }
        renderer.dispose();
        world.dispose();
        font.dispose();
        batch.dispose();
    }

    private  float sp,e,ean,ein,par,kp,kd,ki;
    private boolean Con;
    private float ang;
    private float corx,cory;
    private String str;
    private boolean on;

    @Override
    public void render(float delta) {

        if(on)
        {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            DecimalFormat df = new DecimalFormat("#0.00");

            sp=Pix2Box(Gdx.input.getX())*100;
            if(sp<=20) { sp=20; }
            else if(sp>=56) { sp=56;}

            ang=BodyCr.getAngle();
            if(ang<-.02) {
                BodyCr.setAngularDamping(1000);
                BodyCr.applyTorque(.01f,true);
            }else if (ang>=Math.PI+.01){
                BodyCr.setAngularDamping(1000);
                BodyCr.applyTorque(-.01f,true);}
            else{
                BodyCr.setAngularDamping(.3f);}

            e=sp-BodyCu2.getPosition().x*100;
            kp=.004f;
            kd=.004f;
            ki=.004f;
            if(Gdx.input.justTouched()) { Con=true;}
            if(Con) {
                ein=ein+e*delta;
                par = (kp * e)+((kd*(e-ean)/delta))+(ki*ein); }
            BodyCr.applyTorque(par,true);

            corx=Box2Pix(BodyCu2.getPosition().x-.15f);
            cory=Boy2Piy(BodyCu2.getPosition().y+.1f);
            str=df.format(((BodyCu2.getPosition().x)*100)+45);
            batch.begin();
            font.draw(batch,"Distancia = "+str,corx,cory);
            batch.end();

            world.step(delta,6,2);
            camera.update();
            renderer.render(world,camera.combined);
            ean=e;
        }else {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            world.step(delta,6,2);
            camera.update();
            renderer.render(world,camera.combined);
            on=true;
        }
    }

    private float Boy2Piy(float Cy) {
        return An*R-((An/2)-Cy)*R;
    }

    private float Box2Pix(float Cx) {
        return (Cx+(La/2))*R;
    }

    private float Piy2Boy(float Py) {
        return (An/2)-(Py/R);
    }

    private float Pix2Box(float  Px) {
        return (Px/R)-(La/2);
    }
}

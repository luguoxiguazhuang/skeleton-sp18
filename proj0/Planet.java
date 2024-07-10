import java.util.Objects;

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        double dist = this.calcDistance(p);
        return G*this.mass*p.mass/(dist*dist);
    }
    public double calcForceExertedByX(Planet p){
        double F = this.calcForceExertedBy(p);
        double dist = this.calcDistance(p);
        return F*(p.xxPos-this.xxPos)/dist;
    }
    public double calcForceExertedByY(Planet p){
        double F = this.calcForceExertedBy(p);
        double dist = this.calcDistance(p);
        return F*(p.yyPos-this.yyPos)/dist;
    }
    public double calcNetForceExertedByX(Planet[] planets){
        double NetForce = 0;
        for(Planet p:planets){
            if(!this.equals(p)){
                NetForce+=this.calcForceExertedByX(p);
            }
        }
        return NetForce;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double NetForce = 0;
        for(Planet p:planets){
            if(!this.equals(p)){
                NetForce+=this.calcForceExertedByY(p);
            }
        }
        return NetForce;
    }
    public void update(double dt,double fX,double fY){
        double aX = fX/this.mass;
        double aY = fY/this.mass;
        this.xxVel+=dt*aX;
        this.yyVel+=dt*aY;
        this.xxPos+=dt*this.xxVel;
        this.yyPos+=dt*this.yyVel;
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
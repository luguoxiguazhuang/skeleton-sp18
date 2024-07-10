public class NBody {
    public static double readRadius(String FileName){
        In in = new In(FileName);
        in.readDouble();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String FileName){
        In in = new In(FileName);
        int cnt = in.readInt();
        Planet [] planets = new Planet[cnt];
        in.readDouble();
        for(int i=0;i<cnt;i++){
            planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return planets;
    }
    public static void main(String[] args){
        StdDraw.enableDoubleBuffering();
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double RadiusofUniverse = NBody.readRadius(filename);
        Planet [] planets = NBody.readPlanets(filename);
        StdDraw.setScale(-1.0*RadiusofUniverse,RadiusofUniverse);
        double t = 0;
        for(;t<T;t+=dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0;i<planets.length;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p:planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", RadiusofUniverse);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
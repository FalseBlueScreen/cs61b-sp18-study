public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int planet_num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int planet_num = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanets = new Planet[planet_num];
        for (int i = 0; i < planet_num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img_filename = in.readString();
            Planet b = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img_filename);
            allPlanets[i] = b;
        }
        return allPlanets;
    }   
    public static void main(String[] args){
        StdDraw.enableDoubleBuffering();
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);

        for (double t = 0; t <= T; t = t + dt){
            double[] xForce = new double[allPlanets.length];
            double[] yForce = new double[allPlanets.length];
            for(int i = 0; i < allPlanets.length; i++){
                double NetXForce = allPlanets[i].calcNetForceExertedByX(allPlanets);
                double NetYForce = allPlanets[i].calcNetForceExertedByY(allPlanets);
                xForce[i] = NetXForce;
                yForce[i] = NetYForce;
            }
            for (int i = 0; i < allPlanets.length; i++){
                allPlanets[i].update(dt, xForce[i], yForce[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int j = 0; j < allPlanets.length; j++){
                allPlanets[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }
    }
}

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV; 
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

    }
    
    public double calcDistance(Planet b){
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy(Planet b){
        final double G = 6.67e-11;
        double F = G * mass * b.mass / (calcDistance(b) * calcDistance(b));
        return F;
    }
    
    public double calcForceExertedByX(Planet b){
        double FX = calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
        return FX;
    }

    public double calcForceExertedByY(Planet b){
        double FY = calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
        return FY;
    }

    public double calcNetForceExertedByX(Planet[] allBody){
        double NetFX = 0;
        for (int i = 0; i < allBody.length; i++){
            if (allBody[i].equals(this) == false){
                NetFX = NetFX + calcForceExertedByX(allBody[i]);
            }
        }
        return NetFX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanet){
        double NetFY = 0;
        for (int i = 0; i < allPlanet.length; i++){
            if (allPlanet[i].equals(this) == false ){
                NetFY = NetFY + calcForceExertedByY(allPlanet[i]);
            }
        }
        return NetFY;
    }
}


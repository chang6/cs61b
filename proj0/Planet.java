public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {		
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double distance;
		distance = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + 
                   (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
        distance = Math.sqrt(distance);

        return distance;
	}

	public double calcForceExertedBy(Planet p) {
		double gravConst = 6.67 * Math.pow(10, -11);
		double distance = this.calcDistance(p);
		double f = gravConst * this.mass * p.mass / (distance * distance);

		return f;
	}

	public double calcForceExertedByX(Planet p) {
		double rate = (p.xxPos - this.xxPos) / this.calcDistance(p);
		double fX = this.calcForceExertedBy(p) * rate;

		return fX;
	}

	public double calcForceExertedByY(Planet p) {
		double rate = (p.yyPos - this.yyPos) / this.calcDistance(p);
		double fY = this.calcForceExertedBy(p) * rate;

		return fY;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double fNetX = 0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				fNetX = fNetX + this.calcForceExertedByX(p);
			}
		}
		return fNetX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double fNetY = 0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				fNetY = fNetY + this.calcForceExertedByY(p);
			}
		}
		return fNetY;
	}
	
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}
}

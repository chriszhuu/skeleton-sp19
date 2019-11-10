public class Body {
	final double g = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	String imgFileName;


	public Body(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}
	public double calcDistance(Body b){
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		double r = Math.pow((Math.pow(dx,2) + Math.pow(dy,2)),0.5);
		return r;
	}

	public double calcForceExertedBy(Body b){
		double r = this.calcDistance(b);
		double force = this.mass * b.mass * g / Math.pow(r,2);
		return force;
	}

	public double calcForceExertedByX(Body b){
		double r = this.calcDistance(b);
		double dx = b.xxPos - this.xxPos;
		double force = this.calcForceExertedBy(b);
		double xforce = force * dx / r;
		return xforce;
	}

	public double calcForceExertedByY(Body b){
		double r = this.calcDistance(b);
		double dy = b.yyPos - this.yyPos;
		double force = this.calcForceExertedBy(b);
		double yforce = force * dy / r;
		return yforce;
	}

	public double calcNetForceExertedByX(Body[] bodies){
		double netX = 0.0;
		for (Body b : bodies){
			if (this.equals(b)){
				continue;
			}
			netX += this.calcForceExertedByX(b);
		}
		return netX;
	}

	public double calcNetForceExertedByY(Body[] bodies){
		double netY = 0.0;
		for (Body b : bodies){
			if (this.equals(b)){
				continue;
			}
			netY += this.calcForceExertedByY(b);
		}
		return netY;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += aX * dt;
		this.yyVel += aY * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	public void draw(){
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}

}


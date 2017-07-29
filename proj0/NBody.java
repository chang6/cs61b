public class NBody {
	public static double readRadius(String name) {
		In in = new In(name);
		in.readLine(); // skip the first line
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String planetsTxtPath) {
		In in = new In(planetsTxtPath);
		Planet[] allPlanets = new Planet[5];

		/* Skip the first two lines */
		in.readLine();
		in.readLine();

		for(int i=0; i<5; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allPlanets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		/* Read in the planets and the universe radius from the file described by filename*/
		Planet[] planets = NBody.readPlanets(filename);
		double radius = NBody.readRadius(filename);

		/* Draw the background */
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		

		/* Draw all of the planets */
		for (Planet p : planets) {
			p.draw();
		}
		StdDraw.show(10);

		/* Add the animation */
		for (double t=0; t<=T; t+=dt) {
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for (int i=0; i<5; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i=0; i<5; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) {
				p.draw();
			}
			StdDraw.show(10);
		}

		/* Print out the final state of the universe */
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   				planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}	

	}

}

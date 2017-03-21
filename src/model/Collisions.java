package model;

import physics.Vect;

public class Collisions {

	private double tuc; // Time until Collision
	private Vect velo;	// Velocity

	public Collisions(double t, Vect v) {
		tuc = t;
		velo = v;
	}

	public double getTuc() {
		return tuc;
	}
	
	public Vect getVelo() {
		return velo;
	}
	
}
package ru.gemgame;

public class Vector2<T>
{
	public T X;
	
	public T Y;
	
	public Vector2(T x, T y)
	{
		X = x;
		Y = y;
	}
	
	public boolean Compare(Vector2<T> vector)
	{
		return (vector.X == X && 
				vector.Y == Y);
	}
	
	public Vector2<T> CopyValueOf(Vector2<T> vector)
	{
		return new Vector2<T>(vector.X, vector.Y);
	}
	
	public void Set(Vector2<T> vector)
	{
		this.X = vector.X;
		this.Y = vector.Y;
	}
	
	public void Set(T x, T y)
	{
		this.X = x;
		this.Y = y;
	}
}

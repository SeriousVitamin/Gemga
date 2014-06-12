package ru.gemgame;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.SystemClock;

public class Gem
{
	protected Sprite Sprite;
	protected Vector2<Integer> _coordinates;
	private int _cellSize;
	private int _last;
	
	/** TODO Избавиться от магических чисел */
	protected final Vector2<Integer> Size = new Vector2<Integer>(128, 128);
	
	/** Текущий кадр = 0 */
	protected int currentFrame = 1;
	protected boolean isAnimate = false;

	private int _time;
	
	protected Gem(int x, int y, Bitmap bitmap, int cellSize)
	{
		_cellSize = cellSize;
		_coordinates = new Vector2<Integer>(x, y);
		Sprite = new Sprite(bitmap, x, y, cellSize);
	}
		
	protected void SetCoordinates(Vector2<Integer> coords)
	{
		_coordinates.CopyValueOf(coords);
	}
	
	protected Vector2<Integer> GetCoordinates()
	{
		return _coordinates;
	}
	
	@SuppressLint("WrongCall")	// TODO Избавиться от этой строки
    protected void onDraw(Canvas canvas)
	{
		Sprite.onDraw(canvas);
	}
	
	protected void Animate()
	{
		this.Sprite.isAnimate = true;
	}
	
	
	protected void Drop(int row, int column)
	{
		_time = (int) (SystemClock.elapsedRealtime() * 0.001f);
		
	}
	
	/** Проверка на столкновения */
	public boolean isCollision(float x, float y)
	{
		return (x > _coordinates.X &&
				x < _coordinates.X + _cellSize && 
				y > _coordinates.Y &&
				y < _coordinates.Y + _cellSize);
	}
}

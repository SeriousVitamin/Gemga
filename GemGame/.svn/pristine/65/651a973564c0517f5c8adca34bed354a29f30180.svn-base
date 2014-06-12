package ru.gemgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Board
{
	private final int CELL_COUNT = 8;
	
	/** Глобальные координаты */
	private int _size;
	private Vector2<Integer> _coordinates;
	private List<Gem> _gems = new ArrayList<Gem>();  
	private int _cellSize;
	private List<Bitmap> _bitmaps = new ArrayList();
	private Random _random = new Random(); 
	
	// Пока только для прямоугольной сетки
	private Vector2<Integer> Net[][] = new Vector2 [CELL_COUNT][]; 
	
	protected Board(Vector2<Integer> screenSize, List<Bitmap> bitmaps)
	{
		for (int i = 0; i < bitmaps.size(); i ++)
			_bitmaps.add(bitmaps.get(i));
		
		Init(screenSize);
	}
	
	private void Init(Vector2<Integer> screenSize)
	{
		_size = screenSize.X < screenSize.Y ? screenSize.X : screenSize.Y;
		_cellSize = _size / CELL_COUNT;
		int boardLength = _cellSize * CELL_COUNT;
		
		_coordinates = screenSize.X < screenSize.Y ? 
					new Vector2<Integer>(0, (screenSize.Y - boardLength)/2) :
					new Vector2<Integer>((screenSize.X - boardLength)/2, 0);
		
		/** Инициализация сетки */
		for (int i = 0; i < CELL_COUNT; i ++)
		{
			Net[i] = new Vector2[CELL_COUNT];
		}
		for (int i = 0; i < CELL_COUNT; i ++)
		{
			for (int j = 0; j < CELL_COUNT; j ++)
			{
				Net[i][j] = new Vector2<Integer>(_cellSize * i, _cellSize * j);
			}
		}
		
		/** Инициализация камней */
		for (int i = 0; i < CELL_COUNT; i++)
			for (int j = 0; j < CELL_COUNT; j ++)
			{
				int current = _random.nextInt(_bitmaps.size());
				_gems.add(new Gem(
						_coordinates.X + _cellSize*i, 
						_coordinates.Y + _cellSize*j, 
						_bitmaps.get(current),
						_cellSize));
			}
	}

	private void AddGem(int row, int column)
	{
		
	}
	
	@SuppressLint("WrongCall")
	protected void Draw(Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		paint.setStrokeWidth(3);
		
		/** Рисование сетки */
		for (int i = 0; i <= Net.length; i ++)
		{
			canvas.drawLine(
					_coordinates.X, 
					_coordinates.Y + i * _cellSize, 
					_coordinates.X + _cellSize * CELL_COUNT, 
					_coordinates.Y + i * _cellSize, 
					paint);
			
			canvas.drawLine(
					_coordinates.X + i * _cellSize, 
					_coordinates.Y, 
					_coordinates.X + i * _cellSize, 
					_coordinates.Y + _cellSize * CELL_COUNT, 
					paint);
		}
		
		/** Рисование камней */
		for (Gem gem : _gems)
		{
			gem.onDraw(canvas);
		}
	}
		
	/** Обработка нажатия */
	protected void CollisionHandler(Vector2<Float> coords)
	{
		for (int i = 0; i < _gems.size(); i ++)
		{
			Gem gem = _gems.get(i);
			if (gem.isCollision(coords.X, coords.Y))
			{
				//_gems.remove(i);
				gem.Animate();
			}
		}
	}
}

package ru.gemgame;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;

public class Sprite
{
	/** TODO ���������� �� ���������� ����� */
	/** ����� � ������� */
	private static final int BMP_ROWS = 4;

	/** ������� � ������� */
	private static final int BMP_COLUMNS = 4;

	/** �������� */
	private Bitmap _bitmap;

	private Vector2<Integer> _coordinates;
	private Vector2<Integer> _size;
	
	private int _currentFrame = 1;
	protected boolean isAnimate;
	private int _cellSize;
	private int margin = 3; 
	
	public Sprite(Bitmap bitmap, int x, int y, int cellSize)	
	{
		_bitmap = bitmap;
		_cellSize = cellSize;
		_size = new Vector2<Integer>(bitmap.getWidth() / BMP_COLUMNS, bitmap.getHeight() / BMP_ROWS);
		_coordinates = new Vector2<Integer>(x, y);
	}

	float lastTime = 0.0f;
	private void Update()
	{
		if (!isAnimate)
			return;
		
		float currentTime = SystemClock.elapsedRealtime() * 0.001f;
		
		if (currentTime - lastTime < 0.055f)
			return;
		
		lastTime = currentTime;
		_currentFrame++;
		
		// ���������� ��������
		if (_currentFrame > 16)
		{
			isAnimate = false;
			_currentFrame = 1;
		}
	}

	@SuppressLint("DrawAllocation")	// TODO ���������� �� ���� ������
    protected void onDraw(Canvas canvas)
	{
		Update();
		SystemClock.elapsedRealtime();
		int srcX = (((_currentFrame - 1) % 4)) * _size.X;
		int srcY = ((_currentFrame - 1) / 4) * _size.Y;
		
		Rect src = new Rect(srcX, srcY, srcX + _size.X, srcY + _size.Y);
		RectF dst = new RectF(
				_coordinates.X + margin,
				_coordinates.Y + margin,
				_coordinates.X + _cellSize - margin,
				_coordinates.Y + _cellSize - margin);
		
		canvas.drawBitmap(_bitmap, src, dst, null);
	}

	/** �������� �� ������������ */
	protected boolean isCollision(float x, float y)
	{
		// TODO ������� ����������
		return (x > _coordinates.X &&
				x < _coordinates.X + _size.X && 
				y > _coordinates.Y &&
				y < _coordinates.Y + _size.Y);
	}
}

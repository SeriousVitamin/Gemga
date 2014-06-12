package ru.gemgame;

import java.util.ArrayList;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView
{
	private Board _board;
	
	private List<Sprite> _sprites = new ArrayList<Sprite>();
	
	/** Загружаем спрайт */
	private List<Bitmap> _bitmaps = new ArrayList<Bitmap>(5);
	
	/** Поле рисования */
	// private SurfaceHolder _holder;
	
	/** объект класса GameView */
	private GameManager gameLoopThread;
	
	private Vector2<Float> _touchCoords = new Vector2<Float>(0.0f, 0.0f);
	private boolean _touched = false;
	
	protected long fps;
	
	/** Конструктор */
	public GameView(Context context)
	{
		super(context);
		
		gameLoopThread = new GameManager(this);
		
		/* Рисуем все наши объекты и все все все */
		getHolder().addCallback(new SurfaceHolder.Callback()
		{
			/*** Уничтожение области рисования */
			public void surfaceDestroyed(SurfaceHolder holder)
			{
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry)
				{
					try
					{
						gameLoopThread.join();
						retry = false;
					}
					catch (InterruptedException e)
					{
					}
				}
			}
			
			/** Создание области рисования */
			public void surfaceCreated(SurfaceHolder holder)
			{
				createSprites();
				int height = holder.getSurfaceFrame().height();
				int width = holder.getSurfaceFrame().width();
				_board = new Board(new Vector2<Integer>(width, height), _bitmaps);
				
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}
			
			/** Изменение области рисования */
			public void surfaceChanged(SurfaceHolder holder, int format,
			        int width, int height)
			{
			}
		});
	}
	
	/* Событие столкновения */
	public boolean onTouchEvent(MotionEvent event)
	{
		_touchCoords.X = event.getX();
		_touchCoords.Y = event.getY();
		
		int action = event.getAction();
		   switch(action)
		   {
			   case MotionEvent.ACTION_DOWN:
				   _touched = true;
				   break;
			   case MotionEvent.ACTION_MOVE:
				   _touched = true;
				   break;
			   case MotionEvent.ACTION_UP:
				   _touched = false;
				   break;
			   case MotionEvent.ACTION_CANCEL:
				   _touched = false;
				   break;
			   case MotionEvent.ACTION_OUTSIDE:
				   _touched = false;
				   break;
			   default:
		   }
		
		return true;//super.onTouchEvent(event);
	}
	
	/** Функция, рисующая все спрайты и фон */
	@SuppressLint("WrongCall")
	protected void onDraw(Canvas canvas)
	{
		canvas.drawColor(Color.rgb(0, 0, 0));
		
		Paint paint = new Paint();
		paint.setStrokeWidth(4);
		paint.setTextSize(45);
		paint.setColor(Color.rgb(30, 100, 190));
		
		canvas.drawText(new String().valueOf(fps), 30, 50, paint);
		
		_board.Draw(canvas);
		
		if (_touched)
			//synchronized (getHolder())
			//{
				_board.CollisionHandler(_touchCoords);
			//}
	}
	
	/** Создание всех спрайтов */
	private void createSprites()
	{
		_bitmaps.add(createSprite(R.drawable.green));
		_bitmaps.add(createSprite(R.drawable.orange));
		_bitmaps.add(createSprite(R.drawable.red));
		_bitmaps.add(createSprite(R.drawable.yellow));
		_bitmaps.add(createSprite(R.drawable.blue));
		_bitmaps.add(createSprite(R.drawable.purple));
	}
	
	protected Bitmap createSprite(int resouce)
	{
		return BitmapFactory.decodeResource(getResources(), resouce);
	}
	
	// public void surfaceCreated(SurfaceHolder holder)
	// {
	// createSprites();
	// gameLoopThread.setRunning(true);
	// gameLoopThread.start();
	// }
}
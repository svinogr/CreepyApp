package info.upump.creepyapp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import info.upump.creepyapp.ISwipeController;
import info.upump.creepyapp.R;


/**
 * Created by explo on 02.11.2017.
 */

public class SwipeCallback extends ItemTouchHelper.SimpleCallback {
    private ISwipeController iSwipeController;


    public SwipeCallback(ISwipeController iSwipeController) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.iSwipeController = iSwipeController;

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Bitmap icon;
        Paint p = new Paint();
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            if(dX > 0){
                p.setColor(recyclerView.getResources().getColor(R.color.colorSwipeToFavorite));
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                c.drawRect(background,p);
                icon = BitmapFactory.decodeResource( recyclerView.getResources(),R.drawable.ic_favorite);
                RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                c.drawBitmap(icon,null,icon_dest,p);
            } else {
                p.setColor(recyclerView.getResources().getColor(R.color.colorSwipeToRead));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background,p);
                icon = BitmapFactory.decodeResource( recyclerView.getResources(), R.drawable.ic_eyed);
                RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                c.drawBitmap(icon,null,icon_dest,p);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int positionItem = viewHolder.getAdapterPosition();
        switch (direction) {
            case 8:
             iSwipeController.favorite(positionItem);
                break;
            case 4:
             iSwipeController.read(positionItem);
                break;
        }

    }
}
